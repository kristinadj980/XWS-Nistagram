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
                    <b-button variant="outline-danger"><b-icon icon="search" aria-hidden="true"></b-icon></b-button>
                    </b-input-group-append>
                </b-input-group>
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
        </div>
        <b-card class="content_surface">

        </b-card>
    </div>
</template>

<script>
export default {
    name: 'Profile', 
    data() {
    return {
        searchData: "",
        }
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
         margin-left: 150%;
        width: 50%;
        margin-top: -8%;
    }
</style>