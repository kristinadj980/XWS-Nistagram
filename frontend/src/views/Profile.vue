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
                        <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.name}}
                                    </span>
                        </h5>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="getLikes($event,post)"></b-icon > {{post.numberOfLikes}} likes <b-icon icon="hand-thumbs-down" aria-hidden="true"  @click="getDislikes($event,post)"></b-icon> {{post.numberOfDislikes}} dislikes<span style="margin-left:430px;"></span>
                         <b-icon icon="bookmark" aria-hidden="true" align="right" @click="saveAsFavourite($event,post)"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"  @click="getComments($event,post)"></b-icon> {{post.numberOfComments}}  comments</h4>
                    </b-card>
                </b-tab>

                <b-tab>
                <template #title>
                   <b-icon icon="emoji-heart-eyes" aria-hidden="true"></b-icon><strong>   favourites</strong>
                </template>


                <b-tabs card>
                    <!-- ovaj tab je za sve favourites postove-->
                    <b-tab>
                    <template #title>
                        <b-icon icon="emoji-heart-eyes" aria-hidden="true"></b-icon><strong>   favourites</strong>
                    </template>
                    <b-card class="post_look" v-for="post in favouritePosts" v-bind:key="post.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                        <b-icon font-scale="2" style="margin-top:-38px; margin-left:288px;" icon="plus-circle" aria-hidden="true" @click="addToCollection($event,post)"></b-icon>
                        </b-row>
                        <h6 align="left">{{post.locationDTO.city}},{{post.locationDTO.street}},{{post.locationDTO.objectName}},{{post.locationDTO.country}}</h6>
                        <b-img v-if="!post.fileName.includes(videoText)" thumbnail  v-bind:src="post.imageBytes" alt="Image 1"></b-img>
                        <video v-if="post.fileName.includes(videoText)" autoplay controls v-bind:src="post.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        <h4 align="left">{{post.description}}</h4>
                        <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.name}}
                                    </span>
                        </h5>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="getLikes($event,post)"></b-icon > {{post.numberOfLikes}} likes <b-icon icon="hand-thumbs-down" aria-hidden="true"  @click="getDislikes($event,post)"></b-icon> {{post.numberOfDislikes}} dislikes<span style="margin-left:430px;"></span>
                         <b-icon icon="bookmark" aria-hidden="true" align="right" @click="saveAsFavourite($event,post)"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"  @click="getComments($event,post)"></b-icon> {{post.numberOfComments}}  comments</h4>
                    </b-card>
                    
                </b-tab>
                 <!-- ovo su dodatni tabovi-->
                <b-tab v-for="collection in collections" :key="collection.name" :title="collection.name">
                    <b-card class="post_look" v-for="post in collection.posts" v-bind:key="post.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                        <b-icon font-scale="2" style="margin-top:-38px; margin-left:288px;" icon="plus-circle" aria-hidden="true" @click="addToCollection($event,post)"></b-icon>
                        </b-row>
                        <h6 align="left">{{post.locationDTO.city}},{{post.locationDTO.street}},{{post.locationDTO.objectName}},{{post.locationDTO.country}}</h6>
                        <b-img v-if="!post.fileName.includes(videoText)" thumbnail  v-bind:src="post.imageBytes" alt="Image 1"></b-img>
                        <video v-if="post.fileName.includes(videoText)" autoplay controls v-bind:src="post.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        <h4 align="left">{{post.description}}</h4>
                        <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.name}}
                                    </span>
                        </h5>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="getLikes($event,post)"></b-icon > {{post.numberOfLikes}} likes <b-icon icon="hand-thumbs-down" aria-hidden="true"  @click="getDislikes($event,post)"></b-icon> {{post.numberOfDislikes}} dislikes<span style="margin-left:430px;"></span>
                         <b-icon icon="bookmark" aria-hidden="true" align="right" @click="saveAsFavourite($event,post)"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"  @click="getComments($event,post)"></b-icon> {{post.numberOfComments}}  comments</h4>
                    </b-card>
                </b-tab>
                </b-tabs>
                </b-tab>
            </b-tabs>
            
        </b-card>

        <div> 
          <b-modal ref="modal" hide-footer scrollable title="Profiles who liked your photo" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:#e4e4e4;">
                         <div v-for="user in usersWhoLiked" v-bind:key="user" class="modal-body">
                             
                             <div class="row">
                                <div class=" form-group col">
                                <label > Username : {{user.username}}</label>
                            </div>
                             </div>
                                                       
                         </div>                
                    </div>
               </div>
          </b-modal>
       </div>
        <div> 
          <b-modal ref="modal2" hide-footer scrollable title="Profiles who disliked your photo" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:#e4e4e4; ">
                         <div v-for="user in usersWhoDisliked" v-bind:key="user" class="modal-body">
                             
                             <div class="row">
                                <div class=" form-group col">
                                <label > Username : {{user.username}}</label>
                            </div>
                             </div>
                                                       
                         </div>                
                    </div>
               </div>
          </b-modal>
       </div>
        <div> 
          <b-modal ref="modal3" hide-footer scrollable title="Profiles who commented your photo" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:#e4e4e4; ">
                         <div v-for="user in usersWhoCommented" v-bind:key="user.username" class="modal-body">
                             
                            <div class="row">
                                <div class=" form-group col">
                                     <label>Profile: {{user.usernameFrom}} </label><span style="margin-left:30px;" ></span>
                                     <label > Comment : {{user.comment}}</label><span style="margin-left:30px;" ></span>
                                     <label > Answer : {{user.answer}}</label>
                                </div>
                             </div><span style="margin-left:610px;" ></span>
                             <b-button style="margin-left: 30px;" pill variant="outline-danger" class = "btn btn-lg space_style" @click="sendAnswer($event,user)">Replay</b-button> 
                             </div>
                                            
                    </div>                
                </div>
          </b-modal>
       </div>
       <div> 
          <b-modal ref="modal4" hide-footer scrollable title="Profiles who commented your photo" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:#e4e4e4; ">
                         <div class="modal-body">
                            <div class="row">
                                <div class=" form-group col">  
                                    <input type="text" class="form-control" v-model="answer" placeholder="Answer...">
                                </div>
                             </div>
                            <b-button style="margin-top: 10px; margin-left:640px; " pill variant="outline-danger" class = "btn btn-lg space_style" @click="answerOnComment">Replay</b-button> 
                             </div>
                                            
                    </div>                
                </div>
          </b-modal>
       </div>
       <div> 
          <b-modal ref="modal5" hide-footer scrollable title="Choose or create new collection" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:#e4e4e4; ">
                         <div class="modal-body">
                            <div class="row">
                            <select style="width:250px;" v-model="selectedCollectionID">
                            <option v-for="collection in collections" v-bind:key="collection.id" v-on:click ="addSelected($event, collection.id)">
                            {{collection.name}} {{collection.id}}</option> 
                            </select>
                                <div class=" form-group col">  
                                    <input type="text" class="form-control" v-model="collectionName" placeholder="Enter collection name...">
                                </div>
                             </div>
                            <b-button style="margin-top: 10px; margin-left:640px; " pill variant="outline-danger" class = "btn btn-lg space_style" @click="addPostToCollection">Create</b-button> 
                             </div>
                                            
                    </div>                
                </div>
          </b-modal>
       </div>
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
        usersWhoDisliked:[],
        tags:[],
        usersWhoCommented:[],
        answer:'',
        commentId:'',
        favouritePosts:[],
        fileName:'',
        selectedCollection:'',
        collections:[],
        collectionName:'',
        postId:'',
        selectedCollectionID: '',
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
               this.getMyFavouritePosts(response.data);
               this.getMyCollections(response.data);
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
                    this.usersWhoLiked = response.data
                    this.$refs['modal'].show();
                    console.log(response);                
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },
        getDislikes: async function(event,post){
            const postInfo = {
                myProfile : post.username,
                fileName : post.fileName,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/post/getMyDislikesInfo',postInfo,{ 
                }).then(response => {
                    this.usersWhoDisliked = response.data
                    this.$refs['modal2'].show();
                    console.log(response);                
                }).catch(res => {
                    alert("Error,please try later");
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
        sendAnswer:function(event,user){
            this.$refs['modal3'].hide();
            this.$refs['modal4'].show();
            this.commentId = user.commentId;
        },
        answerOnComment:function(){
            const answerInfo = {
               answer : this.answer,
               id: this.commentId,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/comment/answer',answerInfo,{ 
                }).then(response => {
                    this.usersWhoCommented = response.data
                    this.$refs['modal4'].hide();
                    console.log(response);                
                }).catch(res => {
                    alert("You have already answered");
                    this.$refs['modal4'].hide();
                    console.log(res.response.data.message);

                });
        },
         saveAsFavourite: async function(event,post){
            const postInfo = {
                myProfile : post.username,
                fileName : post.fileName,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/profile/saveFavourites',postInfo,{ 
                }).then(response => {
                    //this.usersWhoLiked = response.data
                    alert("Post is added in the favourites!")
                    console.log(response);                
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },
        getMyFavouritePosts: function(person) {
            this.axios.get('http://localhost:8083/mediaMicroservice/post/getMyFavouritePosts/'+ person.username,)
            .then(response => {
                this.favouritePosts = response.data;
                let video = "mp4";
                
                for(let i=0; i< response.data.length; i++){
                     if(!this.favouritePosts[i].fileName.includes(video)){
                        console.log("slika jeee");
                        this.favouritePosts[i].imageBytes = 'data:image/jpeg;base64,' + this.favouritePosts[i].imageBytes; 
                    }else{
                        console.log("video jeee");
                        this.favouritePosts[i].imageBytes = 'data:video/mp4;base64,' + this.favouritePosts[i].imageBytes;     
                    }            
                } 
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
                    
        },
        addToCollection: function(event,post){
            this.$refs['modal5'].show();
            this.fileName = post.fileName,
            this.username = post.username,
            this.postId = post.id;
            alert(this.postId)
        },
        addPostToCollection : function(){
           const postInfo = {
                myProfile : this.username,
                postId : this.postId,
                collectionName : this.collectionName,
                selectedCollection : this.selectedCollectionID,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/collection/addToCollection',postInfo,{ 
                }).then(response => {
                    //this.usersWhoLiked = response.data
                    alert("Post is added in the favourites!")
                    console.log(response);                
                }).catch(res => {
                    alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },
        getMyCollections: function(person){
        
        this.axios.get('http://localhost:8083/mediaMicroservice/collection/getMyCollections/'+ person.username,)
            .then(response => {
                this.collections = response.data;
                let video = "mp4";
                
                for(let i=0; i< this.collections.length; i++){
                    for(let j=0; j< this.collections[i].posts.length; j++){
                        if(!this.collections[i].posts[j].fileName.includes(video)){
                            this.collections[i].posts[j].imageBytes = 'data:image/jpeg;base64,' + this.collections[i].posts[j].imageBytes; 
                        }else{
                            this.collections[i].posts[j].imageBytes = 'data:video/mp4;base64,' + this.collections[i].posts[j].imageBytes;     
                        }      
                    }      
                } 
                alert("Ok")
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
        },addSelected: function(event,collectionID){
            this.selectedCollection = collectionID;
            alert(this.collectionID)
        }

        
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