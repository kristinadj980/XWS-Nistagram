<template>
    <div id="verificationRequests">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="http://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c521.png">
                <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showHomepage">
                    <b-icon icon="house" aria-hidden="true"></b-icon>Home</b-button>
                <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showVerificationRequests">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Verification requests</b-button>
               
            </span>
                
        </div>
      
         
    </div>
</template>

<script>
export default {
    name: 'VerificationRequests',
    data() {
    return {
        username: "",
        }
    },
    methods:{
        showHomepage: function(){
           window.location.href = "/homePageAdmin";
        },
        showVerificationRequests: function(){
           window.location.href = "/verificationRequests";
        },
        logOut : function(){
            localStorage.removeItem('token');
            window.location.href = "/login";
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