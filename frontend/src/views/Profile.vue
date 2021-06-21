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
        <b-icon
        icon="gear"
        font-scale="3"
        variant="danger"
        aria-hidden="true"  
        v-on:click = "editProfile"
        style="margin-left:95%;
        margin-top:-30%;"
        ></b-icon>
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
                         <h4 align="left" ><b-icon v-if="profile.verificated == true" style="margin-top:-10%;  margin-left:0px;" icon="star-fill" aria-hidden="true"></b-icon>  {{profile.category}}</h4>
                        </h3>
                        <h4 align="left"  v-b-modal.modal6>  <strong>{{this.postsNumber}}</strong> posts <strong>{{this.followersNumber}}</strong> followers <strong >{{this.followingNumber}}</strong> following </h4>
                        <h4 align="left">{{profile.biography}}</h4>
                    </b-col>
                    
            </div>
             <div> 
                <b-modal ref="modal6" id="modal6" hide-footer scrollable size="lg" modal-class="b-modal">
                    <b-tabs content-class="mt-3" align="center">
                        <b-tab title="Followers" active>
                            <b-row text-align-center class="request_look" v-for="f in followers" v-bind:key="f" >
                                <b-col sm="5">
                                    <router-link :to="{ name: 'GeneralProfiles', params: {selectedUsername: f.following }}" class="search-btn">
                                    <h4
                                    align="right"
                                    text-align-center
                                    v-bind:style="{ align: 'center', justify: 'center' }"
                                    >
                                    <b>{{f.following }}</b>
                                    </h4>
                                    </router-link>
                                </b-col>
                                <b-col sm="5">
                                    <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:40%;" v-on:click = "removeFollowera(f.following)">Remove</b-button>
                                </b-col>
                                <hr>
                            </b-row>
                            <b-row style="float:center; margin-top:15px;">
                                <b-button class="btn btn-info btn-lg "  style="background-color:#f08080; width:100%;" v-on:click = "closeFriendsInfo">Close</b-button>
                            </b-row>
                        </b-tab>
                        <b-tab title="Following">
                            <b-row text-align-center class="request_look" v-for="f in following" v-bind:key="f" >
                                <b-col sm="5">
                                    <h4
                                    align="right"
                                    text-align-center
                                    v-bind:style="{ align: 'center', justify: 'center' }"
                                    >
                                    <b>{{f.following }}</b>
                                    </h4>
                                 </b-col>
                                <b-col sm="5">
                                    <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:40%;" v-on:click = "removeFollowing(f.following)">Remove</b-button>
                                </b-col>
                                <hr>
                            </b-row>
                            <b-row style="float:center; margin-top:15px;">
                                <b-button class="btn btn-info btn-lg "  style="background-color:#f08080; width:100%;" v-on:click = "closeFriendsInfo">Close</b-button>
                            </b-row>
                        </b-tab>
                    </b-tabs>
                </b-modal>
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
                        <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes">
                            <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" alt="Image 1"></b-img>
                            <video v-if="post.fileNames[index].includes(videoText)" autoplay controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        </div>
                       <h4 align="left">{{post.description}}</h4>
                       <div  v-for="user in post.taggedUsers" v-bind:key="user.username">
                       <h5 align="left"> @ {{ user.username}}</h5>
                       </div>
                        <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.name}}
                                    </span>
                        </h5>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="getLikes($event,post)"></b-icon > {{post.numberOfLikes}} likes <b-icon icon="hand-thumbs-down" aria-hidden="true"  @click="getDislikes($event,post)"></b-icon> {{post.numberOfDislikes}} dislikes<span style="margin-left:330px;"></span><b-icon icon="bookmark" aria-hidden="true" align="right" @click="saveAsFavourite($event,post)"></b-icon></h5>
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
                        <!--POKUSAJ NEKI-->
                        <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes">
                            <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" alt="Image 1"></b-img>
                            <video v-if="post.fileNames[index].includes(videoText)" autoplay controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        </div>
                        <!--POKUSAJ NEKI-->
                        <h4 align="left">{{post.description}}</h4>
                         <div  v-for="user in post.taggedUsers" v-bind:key="user.username">
                        <h5 align="left"> @ {{ user.username}}</h5>
                        </div>
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
                        <!--POKUSAJ NEKI-->
                        <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes">
                            <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" alt="Image 1"></b-img>
                            <video v-if="post.fileNames[index].includes(videoText)" autoplay controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>

                        </div>
                        <!--POKUSAJ NEKI-->
                        <h4 align="left">{{post.description}}</h4>
                         <div  v-for="user in post.taggedUsers" v-bind:key="user.username">
                       <h5 align="left"> @ {{ user.username}}</h5>
                       </div>
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
                 <!-- Za istoriju lajkovanja -->
                    <b-tab>
                    <template #title>
                        <b-icon icon="hand-thumbs-up" aria-hidden="true"></b-icon><b-icon icon="hand-thumbs-down" aria-hidden="true"></b-icon>
                        <strong> like/dislike history</strong>
                    </template>
                     <div class="row">
                    <div class=" form-group col">
                        <h4 >Likes</h4>
                    </div>
                    <div class=" form-group col">
                        <h4 >Dislikes</h4>
                    </div>
                    <div class=" form-group col">
                        <h4 ></h4>
                    </div>
                   
               </div>
               <div v-for="h in history" :key="h.id">
                    <div class="row" >
                            <div class=" form-group col" style="margin-top:15px;">
                                <label >{{h.usernemeLike}}</label>
                            </div>
                             <div class=" form-group col" style="margin-top:15px;">
                                <label >{{h.usernameDislike}}</label>
                            </div>
                            <div class=" form-group col">
                                <b-button style="margin-top:7px;" pill variant="outline-danger" class = "btn btn-lg space_style" @click="seePost($event,h)">See post</b-button>
                            </div>
                    </div>
               </div>
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
                            <option v-for="collection in collections" v-bind:value="collection.id" v-bind:key="collection.id" v-on:click ="addSelected($event, collection.id)">
                            {{collection.name}}</option> 
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
        <div> 
          <b-modal ref="modal6" hide-footer scrollable title="Post" size="lg" modal-class="b-modal">
               <div modal-class="modal-dialog" role="document">
                    <div class="modal-content" style="background-color:#d4bcce; ">
                         <div class="modal-body">
                           <b-card class="post_look" v-for="post in historyPosts" v-bind:key="post.fileName">
                        <b-row >
                        <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{post.username}}</h4>
                        </b-row>
                        <h6 align="left">{{post.locationDTO.city}},{{post.locationDTO.street}},{{post.locationDTO.objectName}},{{post.locationDTO.country}}</h6>
                        <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes">
                            <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" alt="Image 1"></b-img>
                            <video v-if="post.fileNames[index].includes(videoText)" autoplay controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        </div>
                       <h4 align="left">{{post.description}}</h4>
                        <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.name}}
                                    </span>
                        </h5>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="getLikes($event,post)"></b-icon > {{post.numberOfLikes}} likes <b-icon icon="hand-thumbs-down" aria-hidden="true"  @click="getDislikes($event,post)"></b-icon> {{post.numberOfDislikes}} dislikes<span style="margin-left:330px;"></span><b-icon icon="bookmark" aria-hidden="true" align="right" @click="saveAsFavourite($event,post)"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"  @click="getComments($event,post)"></b-icon> {{post.numberOfComments}}  comments</h4>
                    </b-card>
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
        fileNames:[],
        fileName:'',
        usersWhoLiked:[],
        usersWhoDisliked:[],
        tags:[],
        usersWhoCommented:[],
        answer:'',
        commentId:'',
        favouritePosts:[],
        selectedCollection:'',
        collections:[],
        collectionName:'',
        postId:'',
        selectedCollectionID: '',
        history:[],
        historyPosts:[],
        following: [],
        followingNumber: 0,
        followers: [],
        followersNumber: 0,
        postsNumber: 0,
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
               this.getMyHistory(response.data)
         }).catch(res => {
                       //alert("Error");
                        console.log(res);
                 });
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getAllUsers',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.users = response.data
         }).catch(res => {
                       //alert("Error");
                        console.log(res);
                 });
        
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getFollowingUsers',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
             
            console.log("ooook je"+ response.data);
            this.following = response.data;
            this.followingNumber = response.data.length;
         }).catch(res => {
                       //alert("Error");
                        console.log(res);
                 });

        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getFollowers',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
             
            console.log("ooook je"+ response.data);
            this.followers = response.data;
            this.followersNumber = response.data.length;
         }).catch(res => {
                       //alert("Error");
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
        closeFriendsInfo: function() {
            this.$refs['modal6'].hide();
        },
        getMyPosts: function(person) {
            this.axios.get('http://localhost:8083/mediaMicroservice/post/getMyPosts/'+ person.username,)
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
                        //alert("Error");
                            console.log(res);
                    });
                    
        },
        removeFollowing: function(unfollowUser) {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const unfollowUserInfo ={
                userReceiver : unfollowUser,
            } 
            this.axios.post('http://localhost:8083/profileMicroservice/api/friendRequest/unfollow',unfollowUserInfo, { 
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
        },
        removeFollowera: function(unfollowUser) {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const unfollowUserInfo ={
                userReceiver : unfollowUser,
            } 
            this.axios.post('http://localhost:8083/profileMicroservice/api/friendRequest/removeFollowera',unfollowUserInfo, { 
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
                       // alert("Error");
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
                        //alert("Error");
                            console.log(res);
                    });
                    
        },
        getLikes: async function(event,post){
            const postInfo = {
                myProfile : post.username,
                fileNames : post.fileNames,
                
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
                fileNames : post.fileNames,
                postId : post.id,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/profile/saveFavourites',postInfo,{ 
                }).then(response => {
                    //this.usersWhoLiked = response.data
                    alert("Post is added in the favourites!")
                    console.log(response);                
                }).catch(res => {
                    //alert("Error,please try later");
                    console.log(res.response.data.message);

                });
        },
        getMyFavouritePosts: function(person) {
            this.axios.get('http://localhost:8083/mediaMicroservice/post/getMyFavouritePosts/'+ person.username,)
            .then(response => {
                this.favouritePosts = response.data;
                let video = "mp4";
                
                 for(let i=0; i< this.favouritePosts.length; i++){
                    for(let j=0; j< this.favouritePosts[i].fileNames.length; j++){
                        if(!this.favouritePosts[i].fileNames[j].includes(video)){
                            console.log("usao je u if");
                            this.favouritePosts[i].images[j].imageBytes = 'data:image/jpeg;base64,' + this.favouritePosts[i].images[j].imageBytes;
                        }else{
                            this.favouritePosts[i].images[j].imageBytes = 'data:video/mp4;base64,' + this.favouritePosts[i].images[j].imageBytes;     
                        }      
                    }      
                } 
                
            }).catch(res => {
                        //alert("Error");
                            console.log(res);
                    });
                    
        },
        addToCollection: function(event,post){
            this.$refs['modal5'].show();
            this.fileName = post.fileName,
            this.username = post.username,
            this.postId = post.id;
        },
        addPostToCollection : function(){
            console.log("Sad smo u funkciji i selected id je" + this.selectedCollectionID)
           const postInfo = {
                myProfile : this.username,
                postId : this.postId,
                collectionName : this.collectionName,
                selectedCollection : this.selectedCollectionID,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/collection/addToCollection',postInfo,{ 
                }).then(response => {
                    //this.usersWhoLiked = response.data
                    alert("Post is added in the collection!")
                    this.$refs['modal5'].hide();
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
                        for(let k=0; k< this.collections[i].posts[j].fileNames.length; k++){
                        if(!this.collections[i].posts[j].fileNames[k].includes(video)){
                            this.collections[i].posts[j].images[k].imageBytes = 'data:image/jpeg;base64,' + this.collections[i].posts[j].images[k].imageBytes; 
                        }else{
                            this.collections[i].posts[j].images[k].imageBytes = 'data:video/mp4;base64,' + this.collections[i].posts[j].images[k].imageBytes;     
                        }  
                        }    
                    }      
                } 
               
            }).catch(res => {
                        //alert("Error");
                            console.log(res);
                    });
        },addSelected: function(event,collectionID){
            this.selectedCollection = collectionID;
            alert(this.collectionID)
        },
         getMyHistory: function(person) {
            this.axios.get('http://localhost:8083/mediaMicroservice/post/getLikeDislikeHistory/'+ person.username,)
            .then(response => {
                this.history = response.data;
            }).catch(res => {
                        //alert("Error");
                            console.log(res);
                    });
                    
        },
        seePost : function(event,h){
            this.postId = h.postId;
            if(h.usernemeLike != 'undefine')
            {
            this.username = h.usernemeLike;
            }else{
            this.username = h.usernemeDislike;
            }
            this.findPost()
        },
        findPost : function(){
             const postInfo = {
                id : this.postId,
                username : this.username,
            }
            this.axios.post('http://localhost:8083/mediaMicroservice/post/findPostPictureById',postInfo,{ 
                }).then(response => {
                    this.historyPosts = response.data
                     let video = "mp4";

                for(let i=0; i< this.historyPosts.length; i++){
                    for(let j=0; j< this.historyPosts[i].fileNames.length; j++){
                        if(!this.historyPosts[i].fileNames[j].includes(video)){
                            console.log("usao je u if");
                            this.historyPosts[i].images[j].imageBytes = 'data:image/jpeg;base64,' + this.historyPosts[i].images[j].imageBytes;
                        }else{
                            this.historyPosts[i].images[j].imageBytes = 'data:video/mp4;base64,' + this.historyPosts[i].images[j].imageBytes;     
                        }      
                    }      
                }  
                    this.$refs['modal6'].show();
                    console.log(response);                
                }).catch(res => {
                    //alert("Error,please try later");
                    console.log(res.response.data.message);

                });
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
        margin-left: 155%;
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