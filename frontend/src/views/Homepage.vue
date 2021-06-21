<template>
    <div id="homepage">
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
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "addStories">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add story</b-button>
    
                <!--LOKACIJE-->
               <!--  <b-input-group class=" serach_look1">
                    <b-input-group-append>
                        <input 
                        list="my-list-id1" 
                        v-model="selectedLocation" 
                        class="input_style" 
                        placeholder="enter location..."
                        style="margin-top: 3% !important; width:400px; height:35px;">
                        <datalist id="my-list-id1">
                        <option v-for="location in locations" v-bind:key="location.id">
                            {{ location.country }}, {{location.city}},  {{location.street}}, {{location.objectName}} 
                        </option>
                    </datalist>
                    <router-link :to="{ name: 'SearchPostByLocation', params: {selectedLocation: this.selectedLocation}}" class="search-btn">
                       <b-button style="margin-top: -15% !important;  margin-left: 100% !important;" variant="outline-danger"><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
                    </router-link>
                     </b-input-group-append>

                   
               </b-input-group>    
                <b-input-group class=" serach_look">
                    <b-input-group-append>
                        <input 
                        list="my-list-id" 
                        v-model="selectedTag" 
                        class="input_style" 
                        placeholder="enter tag..."
                        style="margin-top: 3% !important; width:300px; height:35px;">
                       <datalist id="my-list-id">
                            <option v-for="user in tags" v-bind:key="user.id">
                                {{ user.name}} 
                            </option>
                        </datalist> 
                    <router-link :to="{ name: 'SearchPost', params: {selectedTag: this.selectedTag}}" class="search-btn">
                       <b-button style="margin-top: -15% !important;  margin-left: 100% !important;" variant="outline-danger"><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
                    </router-link>

                     </b-input-group-append>
                     <b-input-group-append>
                         <input
                        v-model="selectedUser" 
                        class="input_style" 
                        placeholder="enter username..."
                        style="margin-top: 3% !important; width:300px; height:35px;">
                        <datalist id="my-list-id">
                            <option v-for="user in users" v-bind:key="user.id">
                                {{ user.username }} 
                            </option>
                        </datalist>
                    <router-link :to="{ name: 'GeneralProfiles', params: {selectedUsername: this.selectedUser}}" class="search-btn">
                       <b-button style="margin-top: -20% !important;  margin-left: 100% !important;" variant="outline-danger"><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
                    </router-link>
                
                    </b-input-group-append>
                
                </b-input-group>-->

            </span>
                <span style="float:right;margin:15px">
                    <b-icon icon="person-plus" variant="danger"
                    aria-hidden="true"
                    font-scale="2.5" 
                    v-on:click = "friendRequests"
                    style="margin-right:4%; margin-left: -35%; margin-top: 5%;"
                    ></b-icon>
                    <b-icon 
                    icon="heart"
                    variant="danger"
                    aria-hidden="true"
                    font-scale="2.3"
                    v-on:click="notifications"
                    style="margin-right:8%;margin-left: 5%;  margin-top: 5%;"
                    ></b-icon>
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px; margin-top: -10%;" v-on:click = "logOut">Log Out</b-button>
                </span>
        </div>
        <b-card class="content_surface">

            <!--***************FRIEND'S STORIES************-->
             <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#f08080;margin-left:-1300px;" v-b-modal.modal-1>Show stories</b-button>
                            <b-modal ref="modal-ref" id="modal-1" title="Stories" hide-footer>
                                <b-tabs 
            style="margin-top:70px;" 
            align="center"
            active-nav-item-class="font-weight-bold text-uppercase text-danger"
            active-tab-class="font-weight-bold"
            content-class="mt-3">
                <b-tab active>
                <template #title>
                   <b-icon icon="grid3x3-gap" aria-hidden="true"></b-icon><strong> Stories </strong>
                </template>
                    <b-card class="post_look" v-for="story in stories" v-bind:key="story.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{story.username}}</h4>
                        </b-row>
                        <h6 align="left">{{story.locationDTO.city}},{{story.locationDTO.street}},{{story.locationDTO.objectName}},{{story.locationDTO.country}}</h6>
                        <b-img v-if="!story.fileName.includes(videoText)" thumbnail  v-bind:src="story.imageBytes" alt="Image 1"></b-img>
                        <video v-if="story.fileName.includes(videoText)" autoplay controls v-bind:src="story.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        <h4 align="left">{{story.description}}</h4>
                    </b-card>
                </b-tab>
            </b-tabs>
         </b-modal>
         <!--stories for close friends-->
         <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#f08080;margin-left:-1300px;" v-b-modal.modal-5>Show close friend stories</b-button>
                            <b-modal ref="modal-ref" id="modal-5" title="Close friend stories" hide-footer>
                                <b-tabs 
            style="margin-top:70px;" 
            align="center"
            active-nav-item-class="font-weight-bold text-uppercase text-danger"
            active-tab-class="font-weight-bold"
            content-class="mt-3">
                <b-tab active>
                <template #title>
                   <b-icon icon="grid3x3-gap" aria-hidden="true"></b-icon><strong> Close friend stories </strong>
                </template>
                    <b-card class="post_look" v-for="story in stories1" v-bind:key="story.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{story.username}}</h4>
                        </b-row>
                        <h6 align="left">{{story.locationDTO.city}},{{story.locationDTO.street}},{{story.locationDTO.objectName}},{{story.locationDTO.country}}</h6>
                        <b-img v-if="!story.fileName.includes(videoText)" thumbnail  v-bind:src="story.imageBytes" alt="Image 1"></b-img>
                        <video v-if="story.fileName.includes(videoText)" autoplay controls v-bind:src="story.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        <h4 align="left">{{story.description}}</h4>
                    </b-card>
                </b-tab>
            </b-tabs>
         </b-modal>
         <!--FRIEND'S POSTS-->
             <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                        </b-row>
                        <h6 align="left">{{post.locationDTO.city}},{{post.locationDTO.street}},{{post.locationDTO.objectName}},{{post.locationDTO.country}}</h6>
                        <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes">
                            <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" alt="Image 1"></b-img>
                             <video v-if="post.fileNames[index].includes(videoText)" autoplay controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                        </div>
                         <h4 style="margin-left:500px; margin-top:10px"><b-icon icon="exclamation-circle" aria-hidden="true" align="right" @click="showModalReportPost($event,post)"></b-icon></h4>
                         
                        <h4 align="left" style="margin-top:-35px;">{{post.description}}</h4>
                         <div  v-for="user in post.taggedUsers" v-bind:key="user.username">
                       <h5 align="left"> @ {{ user.username}}</h5>
                       </div>
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
    name: 'Homepage',
    data() {
    return {
        selectedUser:[''],
        users: [],
        friendsNotMuted: [],
        username: "",
        posts: [],
        stories: [],
        stories1: [],
        videoText: "mp4",
        numberOfLikes:0,
        numberOfDislikes:0,
        loggeduser: "",
        closeFriends: [],
        selectedTag:[''],
        selectedLocation:[''],
        tags:[],
        locations:[],
         comments:[],
        comment:'',
        usersWhoCommented:[],
        answer:'',
        commentId:'',
        usernameTo:'',
        usernameFrom:'',
        postId:'',
        description:'',
        selectedPost: [],
        }
    },
    async mounted(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
       
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getFollowingUsers',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.users = response.data
         }).catch(res => {
                       //alert("Error");
                        console.log(res);
                 });
       this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getNotMuted',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.friendsNotMuted = response.data
               this.getFriednsPosts(response.data);
               this.getFriendsStories(response.data);
         }).catch(res => {
                      // alert("Error");
                        console.log(res);
                 });
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getCloseFriends',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
                this.closeFriends=response.data
                this.getCloseFriendsStories(response.data);
                console.log(this.closeFriends);
         }).catch(res => {
                       //alert("Error");
                        console.log(res);
                 });
        
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/loggedUserInfo',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.loggeduser = response.data;
              console.log(response.data.name);
         }).catch(res => {
               //alert(Error)
                console.log(res);
            });
           
            this.axios.get('http://localhost:8083/searchMicroservice/tag/getAllTags',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.tags = response.data
                console.log(this.tags);
         }).catch(res => {
                       //alert("Error");
                        console.log(res);
                 });
                 this.axios.get('http://localhost:8083/mediaMicroservice/location/getLocations',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.locations = response.data
                console.log(this.locations);
         }).catch(res => {
                       //alert("Error");
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
        addStories : function() {
            window.location.href = "/addingStories";
        },
        friendRequests: function() {
            window.location.href = "/friendRequests";
        },notifications: function() {
            window.location.href = "/notifications";
        },
        getFriednsPosts: function(usernames) {

            this.axios.post('http://localhost:8083/mediaMicroservice/post/getFriendsPosts',usernames)
            .then(response => {
                this.posts = response.data;
                let video = "mp4";
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
                        alert("Profile is private");
                            console.log(res);
                    });
        },
        getFriendsStories: function(usernames) {

            this.axios.post('http://localhost:8083/mediaMicroservice/story/getFriendsStories',usernames)
            .then(response => {
                this.stories = response.data;
                let video = "mp4";
                for(let i=0; i< response.data.length; i++){
                     if(!this.stories[i].fileName.includes(video)){
                        this.stories[i].imageBytes = 'data:image/jpeg;base64,' + this.stories[i].imageBytes; 
                    }else{
                        this.stories[i].imageBytes = 'data:video/mp4;base64,' + this.stories[i].imageBytes;     
                    }            
                } 
            }).catch(res => {
                        alert("Profile is private");
                            console.log(res);
                    });
        },
        getCloseFriendsStories: function(usernames) {

            this.axios.post('http://localhost:8083/mediaMicroservice/story/getCloseFriendsStories',usernames)
            .then(response => {
                this.stories1 = response.data;
                let video = "mp4";
                for(let i=0; i< response.data.length; i++){
                     if(!this.stories1[i].fileName.includes(video)){
                        this.stories1[i].imageBytes = 'data:image/jpeg;base64,' + this.stories1[i].imageBytes; 
                    }else{
                        this.stories1[i].imageBytes = 'data:video/mp4;base64,' + this.stories1[i].imageBytes;     
                    }            
                } 
            }).catch(res => {
                        alert("No stories");
                            console.log(res);
                    });
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
        background: #cac6c9; 
        width: 60%;
        height: 100%;
        margin-left: 20%;

    }
    .serach_look{

        margin-left: 170%;
        margin-top: -8%;
    }
    .serach_look1{
        

        margin-left: 138%;
        width: 20%;

        margin-top: -8%;
    }
    .post_look {
        background: #e4e4e4; 
        width: 60%;
        height: 120%;
        margin-left: 20%;
        margin-bottom: 4%;
        margin-top: 2%;
    }
</style>