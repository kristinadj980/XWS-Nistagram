<template >
    <div id="notifications" >
        <div class="homepage_style">
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
                
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "friendRequests">
                    <b-icon icon="person-plus"></b-icon> Friend requests</b-button>
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
        </div>
        <h1 align="center" style="margin-top: 5%; "><b>Notifications</b></h1>
        <hr>
        <b-row text-align-center class="request_look" v-for="notification in notifications" v-bind:key="notification.id" >
            <b-col sm="25">
                <h4
                text-align-center
                >
                <b>{{notification.username }}</b> {{notification.description}}
                </h4>                
            </b-col>
            <hr>
        </b-row>
    </div>
</template>

<script>
export default {
    name: 'Notifications',
    data() {
    return {
        notifications: [],
        }
    },
    mounted(){
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('http://localhost:8083/profileMicroservice/api/notifications/getNotifications',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.notifications = response.data;
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
    .request_look{
        margin-left: 35%;
        padding: 5px;
        width: 30%;
    }
</style>