<template>
    <div id="addingStories">
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
       <form>
            <h4 style="left: 10px;">Choose image </h4>
            <input type="file" name="image" accept="image/png, image/jpeg" id="file" ref="file" v-on:change="handleFileUpload()">
            <h4 for="textarea-large" 
            class="text-left" 
            style="margin-bottom:2%; 
                margin-top: 5% !important;">Description for your image:</h4>
                <b-col sm="12">
                    <b-form-textarea
                    id="textarea-large"
                    v-model="description"
                    placeholder="type here.."
                    ></b-form-textarea>
                </b-col>
                <h4 
                style="margin-bottom:2%; 
                margin-top: 5% !important;">
                    Choose location
                </h4>
                <input 
                list="my-list-id" 
                v-model="selectedLocation" 
                class="input_style" 
                placeholder="enter location.."
                style="margin-top: 1% !important; width:500px;">
                    <datalist id="my-list-id">
                        <option v-for="location in locations" v-bind:key="location.id">
                            {{ location.country }}, {{location.city}},  {{location.street}}, {{location.objectName}} 
                        </option>
                    </datalist>
                <h4 
                style="margin-bottom:2%; 
                margin-top: 5% !important;">
                    Add tags
                </h4>
                <b-form-tags
                    v-model="tags"
                    tag-variant="danger"
                    size="lg"
                    style="background: #999a8d;"
                    separator=" "
                    placeholder="Enter new tags separated by space"
                ></b-form-tags>
                <b-button 
                variant="outline-secondary"  
               
                style="margin-top: 8% !important;
                color: #692d5a;
                width: 240px;"
                >
                <b-icon icon="x-circle" aria-hidden="true"></b-icon> 
                Back
                </b-button>
                <b-button 
                variant="outline-secondary"  
                v-on:click = "saveMedia"
                style="margin-top: -13% !important;
                margin-left: 52%;
                color: #692d5a;
                width: 240px;"
                >
                <b-icon icon="plus-circle" aria-hidden="true"></b-icon> 
                Share story
                </b-button>
  </form>
  </div>
</template>

<script>
export default {
    name: 'AddingStories',
    data() {
    return {
        file: '',
        user:'',
        locations: [],
        selectedLocation:[''],
        description: "",
        tags: [''],
        fileName:'',
        medias:[],
        }
    },
    mounted() {
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
          this.axios.get('http://localhost:8083/profileMicroservice/api/profile/loggedUserInfo',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.user = response.data;
         }).catch(res => {
               alert(Error)
                console.log(res);
            });
            this.axios.get('http://localhost:8083/mediaMicroservice/location/getLocations',{ 
             headers: {
                 'Authorization': 'Bearer ' + token,
             }
         }).then(response => {
              this.locations = response.data;
         }).catch(res => {
               alert(Error)
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
        saveMedia : function() {
            let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
            let formData = new FormData();
            formData.append('file', this.file);

            
            this.axios.post('http://localhost:8083/mediaMicroservice/story/saveImage',formData,{
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': 'Bearer ' + token
                }
                }).then(response => {
                        this.fileName = response.data
                       //alert(this.fileName)
                       //alert("Success")
                       console.log(response.data)
                       this.postPicture();
                       
                  
                    })
                    .catch(response => {
                    console.log(response.data)
                    alert("Eror")
                    
                    });  
        },
        postPicture :function(){  
            const storyInfo= {
                description : this.description,
                location : this.selectedLocation,
                //tags : this.tags,
                username: this.user.username,
                userId: this.user.id,
                fileName : this.fileName,
                 }
          

          this.axios.post('http://localhost:8083/mediaMicroservice/story/addNewStory',storyInfo,{ 
                }).then(response => {
                    alert("Story is posted!");

                    console.log(response);                
                }).catch(res => {
                    alert(res.response.data.message);

                });
        },
        handleFileUpload(){
            this.file = this.$refs.file.files[0];
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
        margin-left: 190%;
        margin-top: -8%;
    }
    form{
        margin-left: 60px;
        width: 500px;
    }
    #addingStories {
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