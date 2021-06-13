<template>
    <div id="generalProfileInfo">
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
            <div class="card header_surface" style="margin-top:10px; border-color: #d4bcce; margin-left:50px;"  >
                  <img class="img-circle img-responsive rounded-circle"  src="https://images.vexels.com/media/users/3/147101/isolated/preview/b4a49d4b864c74bb73de63f080ad7930-instagram-profile-button-by-vexels.png" style=" width:120px; height:120px;"  /> 
                    
                    <b-col style="margin-top:-12%;  margin-left:170px;">
                        <h3 align="left"
                        text-align-center
                        v-bind:style="{ align: 'center', justify: 'center' }"
                        ><b>
                        {{user.username}}
                        </b>
                        </h3>
                        <h4 align="left">  <strong>123</strong> posts <strong>123</strong> followers <strong>123</strong> following </h4>
                        <h4 align="left">{{user.biography}}</h4>
                    </b-col>
            </div>
            <b-button v-if="friendStatus == 'notFriends' || friendStatus == 'rejected'"       
            variant="danger"
            class = "btn btn-lg space_style"
            v-on:click = "follow"
            style="margin-top:25px;
            width:62%;
            margin-left:100px;">
            Follow
            </b-button>
            <b-button v-if="friendStatus == 'waiting'"       
            variant="danger"
            class = "btn btn-lg space_style"
            v-on:click = "cancelRequest"
            style="margin-top:25px;
            width:62%;
            margin-left:100px;">
            Cancel request
            </b-button>
            <b-tabs v-if="user.profileStatus == 'publicProfile' || friendStatus == 'friends'"
            style="margin-top:70px;" 
            align="center" 
            active-nav-item-class="font-weight-bold text-uppercase text-danger"
            active-tab-class="font-weight-bold"
            content-class="mt-3">
                <b-tab active>
                <template #title>
                   <b-icon icon="grid3x3-gap" aria-hidden="true"></b-icon><strong>   posts</strong>
                </template>
                    <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                        </b-row>
                        <h6 align="left">{{post.locationDTO.city}},{{post.locationDTO.street}},{{post.locationDTO.objectName}},{{post.locationDTO.country}}</h6>
                        <b-img v-if="!post.fileName.includes(videoText)" thumbnail  v-bind:src="post.imageBytes" alt="Image 1"></b-img>
                        <video v-if="post.fileName.includes(videoText)" autoplay controls v-bind:src="post.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        <h4 align="left">{{post.description}}</h4>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{post.numberOfLikes}}  likes <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{post.numberOfDislikes}} dislikes <span style="margin-left:430px;"></span> <b-icon icon="bookmark" aria-hidden="true" align="right"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"></b-icon>  comments</h4>
                    </b-card>
                </b-tab>
            </b-tabs>
        </b-card>
    </div>
</template>
<script>
export default {
    name: 'GeneralProfiles', 
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
        choosenUsername:'',
        user:'',
        videoText: "mp4",
        likesNumber:0,
        dislikesNumber:0,
        loggeduser:'',
        friendStatus: '',
        }
    },
    async mounted(){
        this.choosenUsername = this.$route.params.selectedUsername;
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
       
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/loggedUserInfo',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.loggeduser = response.data;
         }).catch(res => {
               alert(Error)
                console.log(res);
            });
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getUserProfile/'+ this.$route.params.selectedUsername)
            .then(response => {
               this.user = response.data
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getFriendStatus/'+ this.$route.params.selectedUsername,{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.friendStatus = response.data;
              console.log(response.data);
         }).catch(res => {
               alert(Error)
                console.log(res);
            });
         this.axios.get('http://localhost:8083/mediaMicroservice/post/getMyPosts/'+ this.$route.params.selectedUsername)
            .then(response => {
                this.posts = response.data;
                let video = "mp4";
                for(let i=0; i< response.data.length; i++){
                     if(!this.posts[i].fileName.includes(video)){
                        this.posts[i].imageBytes = 'data:image/jpeg;base64,' + this.posts[i].imageBytes; 
                    }else{
                        this.posts[i].imageBytes = 'data:video/mp4;base64,' + this.posts[i].imageBytes;     
                    }            
                } 
            }).catch(res => {
                        alert("Profile is private");
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
                fileName : post.fileName,
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
        follow: async function() {
            console.log("u funckiji je");
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const followRequest ={
                userReceiver : this.user.username,
            } 
            this.axios.post('http://localhost:8083/profileMicroservice/api/friendRequest/newRequest',followRequest, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    alert(response.data)
                        console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },cancelRequest: async function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const followRequest ={
                userReceiver : this.user.username,
            } 
            this.axios.post('http://localhost:8083/profileMicroservice/api/friendRequest/cancelRrequest',followRequest, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.friendStatus =response.data;
                        console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },
        refreshPage: function(selectedUser){
            this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getUserProfile/'+ this.$route.params.selectedUsername)
            .then(response => {
               this.user = response.data
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
             this.axios.get('http://localhost:8083/mediaMicroservice/post/getMyPosts/'+ selectedUser)
            .then(response => {
                this.posts = response.data;
                for(let i=0; i< response.data.length; i++){
                        this.posts[i].imageBytes = 'data:image/jpeg;base64,' + this.posts[i].imageBytes;                
                } 
            }).catch(res => {
                        alert("Profile is private");
                            console.log(res);
                    });
            }
        },
        
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