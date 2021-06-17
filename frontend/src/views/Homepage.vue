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
                    <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "proba">
                    <b-icon icon="image" aria-hidden="true"></b-icon> PROBA </b-button>
                <b-input-group class=" serach_look">
                    <b-input-group-append>
                        <input 
                        list="my-list-id" 
                        v-model="selectedTag" 
                        class="input_style" 
                        placeholder="enter username..."
                        style="margin-top: 3% !important; width:400px; height:35px;">
                       <datalist id="my-list-id">
                            <option v-for="user in tags" v-bind:key="user.id">
                                {{ user.name}} 
                            </option>
                        </datalist> 
                    <router-link :to="{ name: 'SearchPost', params: {selectedTag: this.selectedTag}}" class="search-btn">
                       <b-button style="margin-top: -15% !important;  margin-left: 100% !important;" variant="outline-danger"><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
                    </router-link>
                     </b-input-group-append>
                </b-input-group>
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "friendRequests">
                    <b-icon icon="person-plus"></b-icon> Friend requests</b-button>
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
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
                        <b-img v-if="!post.fileName.includes(videoText)" thumbnail width="500px" height="500px" v-bind:src="post.imageBytes" alt="Image 1"></b-img>
                        <video class="video" v-if="post.fileName.includes(videoText)" autoplay controls v-bind:src="post.imageBytes" width="500px" height="500px" ></video>
                        <h4 align="left">{{post.description}}</h4>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{post.numberOfLikes}}  likes <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{post.numberOfDislikes}} dislikes <span style="margin-left:430px;"></span> <b-icon icon="bookmark" aria-hidden="true" align="right"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"></b-icon>  comments</h4>
                    </b-card>
        </b-card>
    </div>
</template>

<script>
export default {
    name: 'Homepage',
    data() {
    return {
        selectedUser:[''],
        users: [],
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
        tags:[],
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
               this.getFriednsPosts(response.data);
               this.getFriendsStories(response.data);
         }).catch(res => {
                       alert("Error");
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
                       alert("Error");
                        console.log(res);
                 });
        
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
            
             this.axios.get('http://localhost:8083/searchMicroservice/tag/getAllTags',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.tags = response.data
                console.log(this.tags);
         }).catch(res => {
                       alert("Error");
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
        },
        getFriednsPosts: function(usernames) {

            this.axios.post('http://localhost:8083/mediaMicroservice/post/getFriendsPosts',usernames)
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
        margin-left: 190%;
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