<template>
    <div id="searchPost">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="http://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c521.png">
                <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showHomepage">
                    <b-icon icon="house" aria-hidden="true"></b-icon>Home</b-button>
                <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showProfile">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Profile</b-button>
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "addPosts">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add post</b-button>
                
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
        </div>
        <b-card class="content_surface" align="left">
                <template #title>
                   <b-icon icon="grid3x3-gap" aria-hidden="true"></b-icon><strong>   posts</strong>
                </template>
                    <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                        </b-row>
                        <h6 align="left">{{post.locationDTO.city}} {{post.locationDTO.street}} {{post.locationDTO.objectName}} {{post.locationDTO.country}}</h6>
                        <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes">
                            <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" alt="Image 1"></b-img>
                             <video v-if="post.fileNames[index].includes(videoText)" autoplay controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                        </div>
                         <h4 style="margin-left:500px; margin-top:10px"><b-icon icon="exclamation-circle" aria-hidden="true" align="right" @click="showModalReportPost($event,post)"></b-icon></h4>
                         
                        <h4 align="left" style="margin-top:-35px;">{{post.description}}</h4>
                         <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.name}}
                                    </span>
                        </h5>
                        
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{post.numberOfLikes}}  likes 
                        <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{post.numberOfDislikes}} dislikes <span style="margin-left:330px;"></span>
                        <b-icon icon="bookmark" aria-hidden="true" align="right"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"  @click="getComments($event,post); selectedPost = post;"></b-icon> {{post.numberOfComments}}  comments </h4>
                        
                    </b-card>
        </b-card>
         <div> 
          <b-modal ref="modal3" hide-footer scrollable title="Profiles who commented photo" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:#e4e4e4; ">
                        <div v-for="user in usersWhoCommented" v-bind:key="user.username" class="modal-body">
                             
                            <div class="row">
                                <div class=" form-group col">
                                     <label><b>{{user.usernameFrom}} </b></label><span style="margin-left:30px;" ></span>
                                     <label> {{user.comment}}</label><span style="margin-left:70px;" ></span>
                                     <label> Answer : {{user.answer}}</label>
                                </div>
                             </div><span style="margin-left:610px;" ></span>
                        </div>
                    <input style="width: 60%; margin-top:10px; margin-left:10px;" type="text" id="post.fileName" v-model="comment"><span style="margin-left:10px;" ></span>
                    <b-icon icon="check-circle" aria-hidden="true" @click="commentPost($event,selectedPost)"></b-icon>      
                    </div>                
                </div>
          </b-modal>
       </div>
        <div> 
          <b-modal ref="modal4" hide-footer scrollable title="Report inappropriate content" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:#e4e4e4; ">
                            <div class="row">
                                <div class=" form-group col">
                                     <span style="margin-left:30px;" ></span>
                                     <input style="margin-left: 10px;" type="text" class="form-control" v-model="description" placeholder="Enter description...">
                                     <span style="margin-left:30px;" ></span>
                                </div>
                                 <b-button style="margin-top: 18px; width:100px ;height:50px;" pill variant="outline-danger" class = "btn btn-lg space_style" @click="reportPost">Report</b-button> 
                             </div><span style="margin-top:30px;" ></span>               
                    </div>                
                </div>
          </b-modal>
       </div>
    </div>
</template>
<script>
export default {
    name: 'SearchPost', 
    data() {
    return {
        searchData: "",
        profile: [],
        username: "",
        name: "",
        surname: "",
        email: "",
        phoneNumber: "",
        birthDate: "",
        gender: "",
        website: "",
        biography: "",
        posts: [],
        selectedUser:[''],
        choosenTag:'',
        user:'',
        videoText: "mp4",
        likesNumber:0,
        dislikesNumber:0,
        loggeduser:'',
        friendStatus: '',
        highlighted: [],
        fileNames:[],
        fileName:'',
        postsNumber: 0,
        comments:[],
        comment:'',
        usersWhoCommented:[],
        answer:'',
        commentId:'',
        usernameTo:'',
        usernameFrom:'',
        postId:'',
        }
    },
    async mounted(){
        this.choosenTag = this.$route.params.selectedTag;
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        console.log(this.choosenTag);

        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/loggedUserInfo',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.loggeduser = response.data;
              console.log(response.data.name);
         }).catch(res => {
               alert(Error)
                console.log(res);
            });
            this.axios.get('http://localhost:8083/profileMicroservice/api/profile/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.profile = response.data;
               console.log(response.data.name);
              
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
                console.log(this.profile.name)
        this.axios.get('http://localhost:8083/searchMicroservice/tag/findPostsByTag/'+ this.$route.params.selectedTag)
            .then(response => {
                this.posts = response.data;
                let video = "mp4";
                this.postsNumber = this.posts.length;
               for(let i=0; i< this.posts.length; i++){
                    for(let j=0; j< this.posts[i].fileNames.length; j++){
                        if(!this.posts[i].fileNames[j].includes(video)){
                            console.log("usao je u if");
                            this.posts[i].images[j].imageBytes = 'data:image/jpeg;base64,' + this.posts[i].images[j].imageBytes;
                        }else{
                            this.posts[i].images[j].imageBytes = 'data:video/mp4;base64,' + this.posts[i].images[j].imageBytes;     
                        }      
                    }      
                } 
            }).catch(res => {
                        alert("No posts for this tag!");
                            console.log(res);
                    });
        
   },
    methods:{
        showHomepage: function(){
           window.location.href = "/homepage";
        },
        showProfile: function(){
           window.location.href = "/profile";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
        },
        addPosts : function() {
            window.location.href = "/addingPosts";
        },
        editProfile: function(){
            window.location.href="/profileInfo";
        },

        likePost: async function(event,post){
            const postInfo = {
                usernameTo : post.username,
                usernameFrom : this.loggeduser.username,
                fileName : post.fileName,
                fileNames : post.fileNames,
                postId: post.id,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/post/likePost',postInfo,{ 
                }).then(response => {
                    alert("Picture is liked!");
                    this.likesNumber = response.data
                    this.numberOfLikes = this.likesNumber
                    
                   // this.$router.push('/generalProfiles/choosenUsername') 
                    console.log(response);                
                }).catch(res => {
                    alert("You have already liked this post");
                    console.log(res.response.data.message);

                });
        },
         dislikePost: async function(event,post){
            const postInfo = {
                usernameTo : post.username,
                usernameFrom : this.loggeduser.username,
                fileNames : post.fileNames,
                postId: post.id,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/post/dislikePost',postInfo,{ 
                }).then(response => {
                    alert("Picture is disliked!");
                    this.dislikesNumber = response.data
                    this.numberOfDislikes = this.likesNumber
                    
                   // this.$router.push('/generalProfiles/choosenUsername') 
                    console.log(response);                
                }).catch(res => {
                    alert("You have already disliked this post");
                    console.log(res.response.data.message);

                });
        },
        
        commentPost: async function(event,post){
            const postInfo = {
                usernameTo : post.username,
                usernameFrom : this.loggeduser.username,
                fileNames : post.fileNames,
                postId: post.id,
                comment : this.comment,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/post/commentPost',postInfo,{ 
                }).then(response => {
                    alert("Picture is commented!");
                    console.log(response);                
                }).catch(res => {
                    alert("Error, please try later!");
                    console.log(res.response.data.message);

                });
        },
         getComments: async function(event,post){
            const postInfo = {
                usernameTo : post.username,
                fileName : post.fileName,
                comment : this.comment,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/post/getMyCommentsInfo',postInfo,{ 
                }).then(response => {
                    this.usersWhoCommented = response.data
                    this.$refs['modal3'].show();
                    console.log(response);                
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },
        showModalReportPost : function(event,post){
               this.$refs['modal4'].show();
               this.usernameTo = post.username,
                this.usernameFrom = this.loggeduser.username,
                this.postId= post.id;
        },
        reportPost: function(){
            const postInfo = {
                usernameTo : this.usernameTo,
                usernameFrom : this.usernameFrom,
                postId: this.postId,
                description : this.description
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/post/reportPost',postInfo,{ 
                }).then(response => {
                    alert("Picture is reported!");
                    this.$refs['modal4'].hide();
                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);
                    console.log(res.response.data.message);

                });
        },
    }    
}
</script>
<style scoped>
    .image_style{
        height: 400px;
        width: 60%;
    }

    .homepage_style{
        background: #f5f1f4; 
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        height: 90px;
    }
    .space_style{
        margin-right:15px;
        margin-left:10px;
    }
    .object_space {
        width: 500px;
        margin-top: 1% !important;
        margin-left: 4% !important;
        margin-bottom: 2% !important;
    }
    .content_surface{
        background: #d4bcce; 
        width: 60%;
        height: 100%;
        margin-left: 20%;

    }
    .header_surface{
        background: #d4bcce; 
        width: 80%;
        height: 30%;
        margin-left: 10%;

    }
    .post_look {
        background: #e4e4e4; 
        width: 60%;
        height: 120%;
        margin-left: 20%;
        margin-bottom: 4%;
        margin-top: 4%;
    }
</style>