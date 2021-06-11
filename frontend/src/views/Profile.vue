<template>
    <div id="profile">
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
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "editProfile">
                    <b-icon icon="gear" aria-hidden="true"></b-icon> Edit profile</b-button>
                <b-input-group class=" serach_look">
                    <b-input-group-append>
                        <input 
                        list="my-list-id" 
                        v-model="selectedUser" 
                        class="input_style" 
                        placeholder="enter username..."
                        style="margin-top: 3% !important; width:400px; height:35px;">
                        <datalist id="my-list-id">
                            <option v-for="user in users" v-bind:key="user.id">
                                {{ user.username }} 
                            </option>
                        </datalist>
                    <router-link :to="{ name: 'GeneralProfiles', params: {selectedUsername: this.selectedUser}}" class="search-btn">
                       <b-button style="margin-top: -15% !important;  margin-left: 100% !important;" variant="outline-danger"><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
                    </router-link>
    
                    </b-input-group-append>
                </b-input-group>
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
        </div>
        <b-card class="content_surface" align="left">
            
             <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#f08080;" v-b-modal.modal-1>Show stories</b-button>
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

          <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#f08080;margin-left:-500" v-b-modal.modal-4>Show highlights</b-button>
                            <b-modal ref="modal-ref4" id="modal-4" title="Highlights" hide-footer>
                                <b-tabs 
            style="margin-top:70px;" 
            align="center" 
            active-nav-item-class="font-weight-bold text-uppercase text-danger"
            active-tab-class="font-weight-bold"
            content-class="mt-3">
                <b-tab active>
                <template #title>
                   <b-icon icon="grid3x3-gap" aria-hidden="true"></b-icon><strong> Highlights</strong>
                </template>
                    <b-card class="post_look" v-for="story in highlighted" v-bind:key="story.fileName">
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

            <div class="card header_surface" style="margin-top:10px; border-color: #d4bcce; margin-left:50px;"  >
                  <img class="img-circle img-responsive rounded-circle"  src="https://images.vexels.com/media/users/3/147101/isolated/preview/b4a49d4b864c74bb73de63f080ad7930-instagram-profile-button-by-vexels.png" style=" width:120px; height:120px;"  /> 
                    
                    <b-col style="margin-top:-12%;  margin-left:170px;">
                        <h3 align="left"
                        text-align-center
                        v-bind:style="{ align: 'center', justify: 'center' }"
                        ><b>
                        {{profile.username}}
                        </b>
                        </h3>
                        <h4 align="left">  <strong>123</strong> posts <strong>123</strong> followers <strong>123</strong> following </h4>
                        <h4 align="left">{{profile.biography}}</h4>
                    </b-col>
            </div>
            <b-tabs 
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
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="getLikes($event,post)"></b-icon > {{post.numberOfLikes}} likes <b-icon icon="hand-thumbs-down" aria-hidden="true" ></b-icon> {{post.numberOfDislikes}} dislikes<span style="margin-left:430px;"></span> <b-icon icon="bookmark" aria-hidden="true" align="right"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"></b-icon>  comments</h4>
                    </b-card>
                </b-tab>

                <b-tab>
                <template #title>
                   <b-icon icon="emoji-heart-eyes" aria-hidden="true"></b-icon><strong>   favourites</strong>
                </template>
                    
                </b-tab>
            </b-tabs>
            
        </b-card>
    </div>
</template>

<script>
export default {
    name: 'Profile', 
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
        users: [],
        selectedUser:[''],
        stories: [],
        highlighted:[],
        videoText: "mp4",
        numberOfLikes:0,
        numberOfDislikes:0,
        usersWhoLiked:[],

        }
    },
    mounted(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.profile = response.data;
               this.getMyPosts(response.data);
               this.getMyStories(response.data);
               this.getHighlightedStories(response.data);
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getAllUsers',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.users = response.data
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
   },
    methods:{
         toggle () {
        this.show = !this.show
        },
        cancel() {
            this.$refs['modal-ref'].hide();
        },
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
        editProfile: function(){
            window.location.href="/profileInfo";
        },
        getMyPosts: function(person) {
            this.axios.get('http://localhost:8083/mediaMicroservice/post/getMyPosts/'+ person.username,)
            .then(response => {
                this.posts = response.data;
                let video = "mp4";
                
                for(let i=0; i< response.data.length; i++){
                     if(!this.posts[i].fileName.includes(video)){
                        console.log("slika jeee");
                        this.posts[i].imageBytes = 'data:image/jpeg;base64,' + this.posts[i].imageBytes; 
                    }else{
                        console.log("video jeee");
                        this.posts[i].imageBytes = 'data:video/mp4;base64,' + this.posts[i].imageBytes;     
                    }            
                } 
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
                    
        },
       
        getMyStories: function(person) {
            this.axios.get('http://localhost:8083/mediaMicroservice/story/getMyStories/'+ person.username,)
            .then(response => {
                this.stories = response.data;
                let video = "mp4"
                for(let i=0; i< response.data.length; i++){
                        if(!this.stories[i].fileName.includes(video)){
                        console.log("slika jeee");
                        this.stories[i].imageBytes = 'data:image/jpeg;base64,' + this.stories[i].imageBytes; 
                    }else{
                        console.log("video jeee");
                        this.stories[i].imageBytes = 'data:video/mp4;base64,' + this.stories[i].imageBytes;     
                    }                            
                } 
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
                    
        },
        getHighlightedStories: function(person) {
            this.axios.get('http://localhost:8083/mediaMicroservice/story/getHighlightedStories/'+ person.username,)
            .then(response => {
                this.highlighted = response.data;
                let video = "mp4"
                for(let i=0; i< response.data.length; i++){
                        if(!this.highlighted[i].fileName.includes(video)){
                        console.log("slika jeee");
                        this.highlighted[i].imageBytes = 'data:image/jpeg;base64,' + this.highlighted[i].imageBytes; 
                    }else{
                        console.log("video jeee");
                        this.highlighted[i].imageBytes = 'data:video/mp4;base64,' + this.highlighted[i].imageBytes;     
                    }                            
                } 
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
                    
        },
        getLikes: async function(event,post){
            const postInfo = {
                myProfile : post.username,
                fileName : post.fileName,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/post/getMyLikesInfo',postInfo,{ 
                }).then(response => {
                    alert("Success!");
                    this.usersWhoLiked = response.data
                    
                   // this.$router.push('/generalProfiles/choosenUsername') 
                    console.log(response);                
                }).catch(res => {
                    alert("Error,please try later");
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
    .serach_look{
         margin-left: 150%;
        width: 50%;
        margin-top: -8%;
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