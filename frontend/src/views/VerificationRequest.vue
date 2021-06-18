<template>
    <div id="verificationRequest">
        <div class="homepage_style ">
           <span style="float: left; margin: 15px;">
                <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; margin-right:10px;"
                src="http://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c521.png">
                <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showHomepage">
                    <b-icon icon="house" aria-hidden="true"></b-icon>Home</b-button>
                <b-button  pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showProfile">
                    <b-icon icon="person" aria-hidden="true"></b-icon>Profile</b-button>
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "showProfile">
                    <b-icon icon="image" aria-hidden="true"></b-icon> Add post</b-button>
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "editProfile">
                    <b-icon icon="gear" aria-hidden="true"></b-icon> Edit profile</b-button>
                <b-button pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "verificationRequest">
                    <b-icon icon="gear" aria-hidden="true"></b-icon> Verification request</b-button>
                
            </span>
                <span  style="float:right;margin:15px">
                    <b-button pill variant="outline-danger" class = "btn btn-lg btn-light" style="margin-right:20px;" v-on:click = "logOut">Log Out</b-button>
                </span>
             </div>
            <!--****** -->    
            <form>
            <h4 style="left: 10px;">Name: {{name}} </h4>
            <h4 style="left: 10px;">Surname: {{surname}}</h4>
            <select style="width:250px;" v-model="selectedCategory">
                <option v-for="item in this.categories"  v-on:click ="addCategoryTolist($event, item)" v-bind:key="item.id" >
                {{item}}</option> 
            </select>
            <h4 style="left: 10px; margin-top: 50px;" >Choose document image</h4>
             <input type="file" name="image" accept="image/png, image/jpeg, video/mp4,video/x-m4v,video/*" id="file" ref="file" v-on:change="handleFileUpload()">
           <b-button style="margin-left: 370px; margin-top: 50px;" pill variant="outline-danger" class = "btn btn-lg space_style" v-on:click = "sendVerificationRequest">
                <b-icon icon="check-circle" aria-hidden="true"></b-icon> Send</b-button>
           </form>
    <!--****** -->    
        
    </div>
</template>

<script>
export default {
    name: 'VerificationRequest',
    data() {
    return {
        profile: "",
        username: "",
        name: "",
        surname: "",
        email: "",
        categories:["influencer","sports","media","business","brand","organization"],
        user:'',
        file:'',
        fileName:'',
        selectedCategory:'',
        formData:''
        }
    },
    mounted(){
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         
          this.axios.get('http://localhost:8083/profileMicroservice/api/profile/account',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.user = response.data;
              this.name = this.user.name;
              this.surname = this.user.surname;
         }).catch(res => {
               alert(Error)
                console.log(res);
            });
   },
    methods:{
        toggle () {
        this.show = !this.show
        },
        cancelStories() {
            this.$refs['modal-ref3'].hide();
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
        editProfile: function(){
            window.location.href="/profileInfo";
        },
        verificationRequest(){
            window.location.href = "/verificationRequest";
        },
        handleFileUpload(){
            this.file = this.$refs.file.files[0];
        },
        addCategoryTolist : function(event, item) {
            this.selectedCategory = item;
            alert(this.selectedCategory)
        },
        sendRequest :function(){  
        let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            const info= {
                name : this.name,
                surname : this.surname,
                verificationCategory : this.selectedCategory,
                fileName : this.fileName,
                username : this.user.username,
                 }
          
          this.axios.post('http://localhost:8083/profileMicroservice/api/profile/verificationRequest',info,{ 
               headers: {
                 'Authorization': 'Bearer ' + token,
             }
                }).then(response => {
                    alert("Request is sent!");
                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);
                });
        },
        sendVerificationRequest: function(){
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            let formData = new FormData();
            formData.append('file', this.file);
            
            this.axios.post('http://localhost:8083/mediaMicroservice/post/saveImageForRequest',formData,{
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': 'Bearer ' + token
                }
                }).then(response => {
                        this.fileName = response.data
                       //alert(this.fileName)
                       //alert("Success")
                       console.log(response.data)
                       this.sendRequest();
                       
                  
                    })
                    .catch(response => {
                    console.log(response.data)
                    alert("Eror")
                    
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
        background: #cac6c9; 
        width: 60%;
        height: 100%;
        margin-left: 20%;

    }
    .serach_look{
        margin-left: 190%;
        margin-top: -8%;
    }
    form{
        margin-left: 60px;
        width: 500px;
    }
    #verificationRequest {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: left;
        color: #692d5a;
        margin: auto;
        margin-top: 40px;
        margin-bottom: 40px;
        width: 40%;
        border: 4px solid #692d5a;
        padding: 40px;
        }
</style>