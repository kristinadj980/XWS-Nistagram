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
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "editProfile">
                    <b-icon icon="gear" aria-hidden="true"></b-icon> Edit profile</b-button>
                <b-input-group class=" serach_look">
                    <b-form-input placeholder="search.."></b-form-input>
                    <b-input-group-append>
                        <input 
                        list="my-list-id" 
                        v-model="selectedUser" 
                        class="input_style" 
                        placeholder="enter username..."
                        style="margin-top: 1% !important; width:500px;">
                            <datalist id="my-list-id">
                        <option v-for="user in users" v-bind:key="user.id">
                            {{ user.username }} 
                        </option>
                    </datalist>
                    <b-button variant="outline-danger"><b-icon icon="search" aria-hidden="true" v-on:click = "searchUser"></b-icon></b-button>
                    </b-input-group-append>
                </b-input-group>
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
                        <b-img thumbnail  v-bind:src="post.imageBytes" alt="Image 1"></b-img>
                        <h4 align="left">{{post.description}}</h4>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true"></b-icon>  likes <b-icon icon="hand-thumbs-down" aria-hidden="true"></b-icon> <span style="margin-left:430px;"></span> <b-icon icon="bookmark" aria-hidden="true" align="right"></b-icon></h5>
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
        getMyPosts: function(person) {
            this.axios.get('http://localhost:8083/mediaMicroservice/post/getMyPosts/'+ person.username,)
            .then(response => {
                this.posts = response.data;
                for(let i=0; i< response.data.length; i++){
                        this.posts[i].imageBytes = 'data:image/jpeg;base64,' + this.posts[i].imageBytes;                
                } 
            }).catch(res => {
                        alert("Error");
                            console.log(res);
                    });
                    
        },
        searchUser: function(selectedUser) {
            this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getUserProfile/'+ selectedUser,)
            .then(response => {
               this.users = response.data
            }).catch(res => {
                        alert("Error");
                            console.log(res);
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