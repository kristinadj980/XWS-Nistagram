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
         <!-- table -->
         <div style = "background-color:#f5f1f4; margin: auto; width: 60%;border: 2px solid #692d5a;padding: 10px;margin-top:45px;">
               <div class="row" style="margin-top: 10px;">
                    <div class=" form-group col" >
                        <h4 >Request from</h4>
                    </div>
                    <div class=" form-group col">
                        <h4> Category</h4>
                    </div>
                    <div class=" form-group col" >
                        <h4 >Request status</h4>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>
                    <div class=" form-group col">
                        <label ></label>
                    </div>  
               </div>
               <div v-for="request in requests"   v-bind:key="request.id">
                    <div class="row" >
                            <div class=" form-group col" style="margin-top: 10px;margin-left: 30px;">
                                <h5>{{request.name}}  {{request.surname}}</h5>
                            </div>
                             <div class=" form-group col" style="margin-top: 10px;margin-left: 70px;">
                                <h5 >{{request.category}}</h5>
                            </div>
                            <div  class=" form-group col" style="margin-top: 10px;margin-left: 70px;">          
                                <h5 >{{request.requestStatus}}</h5>
                            </div>
                            <div class=" form-group col">
                                <b-button style="margin-left: 150px;" pill variant="outline-danger" class = "btn btn-lg space_style">Approve</b-button>
                            </div>
                            <div class=" form-group col" >
                               <b-button style="margin-left: -30px;" pill variant="outline-danger" class = "btn btn-lg space_style">Reject</b-button>
                            </div>
                    </div>
               </div>
         </div>
      
         
    </div>
</template>

<script>
export default {
    name: 'VerificationRequests',
    data() {
    return {
        username: "",
        requests:[],
        }
    },
     mounted(){
     let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getAllRequests',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
               this.requests = response.data;
         }).catch(res => {
                       alert("Error");
                        console.log(res);
                 });
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