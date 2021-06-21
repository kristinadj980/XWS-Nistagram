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
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "probala">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add post</b-button>
                
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
        </div>
        <b-card class="content_surface" align="left">

<!--  HIGHLIGHTS -->
             <b-button  class="btn btn-info btn-lg space_style"  style="background-color:#f08080;margin-left:100px;" v-b-modal.modal-5>Show highlights</b-button>
                            <b-modal ref="modal-ref" id="modal-5" title="Highlights" hide-footer>
                                <b-tabs 
            style="margin-top:70px;" 
            align="center"
            active-nav-item-class="font-weight-bold text-uppercase text-danger"
            active-tab-class="font-weight-bold"
            content-class="mt-3">
                <b-tab active>
                <template #title>
                   <b-icon icon="grid3x3-gap" aria-hidden="true"></b-icon><strong> Highlights </strong>
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

        <b-button style="margin-left:1000px; margin-top:100px;" variant="outline-danger" size="lg"  v-on:click = "blockUser">
                    Block 
                </b-button>

            <b-dropdown
            variant="outline-danger" 
            class="m-2" 
            menu-class="w-100" 
            v-if="friendStatus == 'approved' || friendStatus == 'friends'"
            style="left:87%;">
                <template #button-content>
                   <b-icon icon="three-dots" aria-hidden="true" ></b-icon>
                </template>
                <b-dropdown-item >Mute</b-dropdown-item>
                <b-dropdown-item>Block</b-dropdown-item>
            </b-dropdown>
            <b-icon 
            v-if="friendStatus == 'approved' || friendStatus == 'friends'" 
            icon="bell" 
            variant="danger"
            style="margin-left:75%;" 
            aria-hidden="true"
            font-scale="2"
            v-on:click="getFriendNotifications"
            v-b-modal.modal5>
            </b-icon>
            <div> 
                <b-modal ref="modal5" id="modal5" hide-footer scrollable title="Notifications" size="lg" modal-class="b-modal">
                    <b-row>
                        <b-col sm="5"><h5 class ="text-justify top-buffer" align="left" style="margin-left:100px;"> Messages </h5></b-col>
                        <b-col sm="1">
                            <b-button  variant="outline-danger" size="lg" class = " mb-2 btn btn-lg space_style" v-on:click = "messageNotificaiton" >
                                <b-icon v-if="friendNotifications.message == true" icon="toggle-on" aria-hidden="true" tooltip="click to disable tags"></b-icon> 
                                <b-icon v-if="friendNotifications.message == false" icon="toggle-off" aria-hidden="true"  tooltip="click to allow tags"></b-icon> 
                            </b-button>
                        </b-col>
                    </b-row>

                    <b-row>
                        <b-col sm="5"><h5 class ="text-justify top-buffer" align="left" style="margin-left:100px;"> Posts </h5></b-col>
                        <b-col sm="1">
                            <b-button  variant="outline-danger" size="lg" class = " mb-2 btn btn-lg space_style" v-on:click = "postNotificaiton" >
                                <b-icon v-if="friendNotifications.post == true" icon="toggle-on" aria-hidden="true" tooltip="click to disable tags"></b-icon> 
                                <b-icon v-if="friendNotifications.post == false" icon="toggle-off" aria-hidden="true"  tooltip="click to allow tags"></b-icon> 
                            </b-button>
                        </b-col>
                    </b-row>

                    <b-row>
                        <b-col sm="5"><h5 class ="text-justify top-buffer" style="margin-left:100px;" align="left"> Stories </h5></b-col>
                        <b-col sm="1">
                            <b-button variant="outline-danger" size="lg" class=" mb-2 btn btn-lg space_style " v-on:click = "storyNotificaiton">
                                <b-icon v-if="friendNotifications.story == true" value="true" icon="toggle-on" aria-hidden="true" tooltip="click to disable messages"></b-icon>
                                <b-icon v-if="friendNotifications.story == false" value="false" icon="toggle-off" aria-hidden="true"  tooltip="click to allow messages"></b-icon> 
                            </b-button>
                        </b-col>
                    </b-row>

                    <b-row>
                        <b-col sm="5"><h5 class ="text-justify top-buffer" style="margin-left:100px;" align="left"> Comments </h5></b-col>
                        <b-col sm="1">
                            <b-button variant="outline-danger" size="lg" class=" mb-2 btn btn-lg space_style " v-on:click = "commentNotificaiton">
                                <b-icon v-if="friendNotifications.comment == true" value="true" icon="toggle-on" aria-hidden="true" tooltip="click to disable messages"></b-icon>
                                <b-icon v-if="friendNotifications.comment == false" value="false" icon="toggle-off" aria-hidden="true"  tooltip="click to allow messages"></b-icon> 
                            </b-button>
                        </b-col>
                    </b-row>

                </b-modal>
            </div>
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
                        <h4 align="left">  <strong>{{postsNumber}}</strong> posts <strong>123</strong> followers <strong>123</strong> following </h4>
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
            <b-button v-if="friendStatus == 'approved' || friendStatus == 'friends'"       
            variant="danger"
            class = "btn btn-lg space_style"
            v-on:click = "unfollow"
            style="margin-top:25px;
            width:62%;
            margin-left:100px;">
            Unfollow
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
                         <!--POKUSAJ NEKI-->
                        <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes">
                            <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" alt="Image 1"></b-img>
                             <video v-if="post.fileNames[index].includes(videoText)" autoplay controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        </div>
                        <!--POKUSAJ NEKI-->
                        <h4 style="margin-left:500px; margin-top:10px"><b-icon icon="exclamation-circle" aria-hidden="true" align="right" @click="showModalReportPost($event,post)"></b-icon></h4>
                         
                        <h4 align="left" style="margin-top:-35px;">{{post.description}}</h4>
                        <h5 align="left"> <span v-for="(user,t) in post.taggedUsers" :key="t">
                                        @{{user.username}}
                                    </span>
                        </h5>
                         <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.name}}
                                    </span>
                        </h5>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{post.numberOfLikes}}  likes
                        <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{post.numberOfDislikes}} dislikes <span style="margin-left:330px;"></span> 
                        <b-icon icon="bookmark" aria-hidden="true" align="right"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"  @click="getComments($event,post); selectedPost = post;"></b-icon> {{post.numberOfComments}}  comments </h4>
                        </b-card>
                </b-tab>
            </b-tabs>
        </b-card>
        <div> 
          <b-modal ref="modal3" hide-footer scrollable title="Profiles who commented photo" size="lg" modal-class="b-modal">
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
        highlighted: [],
        comments:[],
        comment:'',
        usersWhoCommented:[],
        answer:'',
        commentId:'',
        postId:'',
        usernameTo:'',
        usernameFrom:'',
        description:'',
        friendNotifications: [],
        proba: "ana",
        selectedPost: [],
        postsNumber: 0,
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
                this.postsNumber = response.data.length;

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
                     this.axios.get('http://localhost:8083/mediaMicroservice/story/getHighlightedStories/'+this.$route.params.selectedUsername,)
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
        messageNotificaiton: async function() {
            const messageN = {
                id: this.friendNotifications.id,
                message : this.friendNotifications.message,
                post: null,
                story: null,
                comment: null
            }

            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('http://localhost:8083/profileMicroservice/api/friendNotificationsController/changeStatus',messageN, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.friendNotifications = response.data;
                    console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },
        postNotificaiton: async function() {
            const messageN = {
                id: this.friendNotifications.id,
                message : null,
                post: this.friendNotifications.post,
                story: null,
                comment: null
            }

            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('http://localhost:8083/profileMicroservice/api/friendNotificationsController/changeStatus',messageN, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.friendNotifications = response.data;
                    console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },
        storyNotificaiton: async function() {
            const messageN = {
                id: this.friendNotifications.id,
                message : null,
                post: null,
                story: this.friendNotifications.story,
                comment: null
            }

            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('http://localhost:8083/profileMicroservice/api/friendNotificationsController/changeStatus',messageN, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.friendNotifications = response.data;
                    console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },
        commentNotificaiton: async function() {
            const messageN = {
                id: this.friendNotifications.id,
                message : null,
                post: null,
                story: null,
                comment: this.friendNotifications.comment
            }

            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.post('http://localhost:8083/profileMicroservice/api/friendNotificationsController/changeStatus',messageN, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
                .then(response => {
                    this.friendNotifications = response.data;
                    console.log(response);
                })
                .catch(response => {
                    alert("Please, try later.")
                    console.log(response);
                })
        },probala: function(){
            
            const postInfo = {
                username : this.proba,
            }
            this.axios.post('http://localhost:8083/profileMicroservice/api/notifications/postNotify', postInfo,{ 
                }).then(response => {
                    alert("Picture is reported!");
                    this.$refs['modal4'].hide();
                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);
                    console.log(res.response.data.message);

                });
        },
        likePost: async function(event,post){
            const postInfo = {
                usernameTo : post.username,
                usernameFrom : this.loggeduser.username,
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
                comment : this.comment,
                postId: post.id,
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
        follow: function() {
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
        },
        unfollow: async function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const unfollowUserInfo ={
                userReceiver : this.user.username,
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
        cancelRequest: async function() {
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
        },getFriendNotifications: function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.get('http://localhost:8083/profileMicroservice/api/friendNotificationsController/friendNotifications/'+ this.$route.params.selectedUsername, { 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }})
            .then(response => {
               this.friendNotifications = response.data
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
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
            },
        blockUser: function(){
            console.log(this.user.username);  
             let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            this.axios.get('http://localhost:8083/profileMicroservice/api/profile/account',{ 
                headers: {
                 'Authorization': 'Bearer ' + token,
                }
                }).then(response => {
               this.profile = response.data;
               console.log(this.profile.username);

                 }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
        console.log(this.user.username);
            this.axios.post('http://localhost:8083/profileMicroservice/api/profile/blockUser',this.user.username,{ 
                headers: {
                    'Authorization': 'Bearer ' + token,
                }
                }).then(response => {
                    
                    alert("Successfully blocked");
                    console.log(response);                
                }).catch(res => {
                    alert("Error");
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