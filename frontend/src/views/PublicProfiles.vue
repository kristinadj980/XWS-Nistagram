<template>
    <div id="publicProfiles">
        <b-card class="header_look" >
        <div class="card header_surface" style="margin-top:10px; border-color: #d4bcce; margin-left:50px;"  >
            <img class="img-circle img-responsive rounded-circle"  src="https://images.vexels.com/media/users/3/147101/isolated/preview/b4a49d4b864c74bb73de63f080ad7930-instagram-profile-button-by-vexels.png" style=" width:120px; height:120px;"  /> 
                    
            <b-col style="margin-top:-18%;  margin-left:170px;">
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
            <b-col>
            <b-button       
            variant="danger"
            class = "btn btn-lg space_style"
            v-on:click = "follow"
            style="margin-top:25px;
            width:62%;
            margin-left:100px;"
            v-b-modal.modal-2>
            Follow
            </b-button>
            </b-col>
        </div>
        </b-card>
        <b-card class="post_look" v-for="post in posts" v-bind:key="post.fileName">
            <b-row >
                <h4 align="left"><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{post.username}}</h4>
            </b-row>
                        <h6 align="left">{{post.locationDTO.city}},{{post.locationDTO.street}},{{post.locationDTO.objectName}},{{post.locationDTO.country}}</h6>
                         <!--POKUSAJ NEKI-->
                        <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes">
                            <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" width="400" height="400" alt="Image 1"></b-img>
                             <video v-if="post.fileNames[index].includes(videoText)" controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
                        </div>
                        <!--POKUSAJ NEKI-->
                        <h4 style="margin-left:500px; margin-top:10px"><b-icon icon="exclamation-circle" aria-hidden="true" align="right" @click="showModalReportPost($event,post)"></b-icon></h4>
                         
                        <h4 align="left" style="margin-top:-35px;">{{post.description}}</h4>
                         <h5 align="left"><span v-for="(tag,t) in post.tags" :key="t">
                                        #{{tag.name}}
                                    </span>
                        </h5>
                        <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true" @click="likePost($event,post)"></b-icon>{{post.numberOfLikes}}  likes
                        <b-icon icon="hand-thumbs-down" aria-hidden="true" @click="dislikePost($event,post)"></b-icon>{{post.numberOfDislikes}} dislikes <span style="margin-left:330px;"></span> 
                        <b-icon icon="bookmark" aria-hidden="true" align="right"></b-icon></h5>
                        <h4 align="left"><b-icon icon="chat-square" aria-hidden="true"  @click="getComments($event,post); selectedPost = post;"></b-icon> {{post.numberOfComments}}  comments </h4>
        </b-card>
        <b-modal ref="modal-ref2" id="modal-2"  hide-footer>
            <img class="image_style " title="Nistagram" style="width: 50px; height: 50px; left:10px;"
                        src="http://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c521.png">
            <h3 align="center">Please register if you want to see more! </h3>
            <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:5cm;" v-on:click = "register">Register</b-button>
                                                
        </b-modal>
    </div>
</template>
<script>
export default {
    name: 'PublicProfiles', 
    data() {
    return {
        user:'',
        videoText: "mp4",
        likesNumber:0,
        dislikesNumber:0,
        postsNumber: 0,
        }
    },
    async mounted(){
        this.choosenUsername = this.$route.params.selectedUsername;
        
        this.axios.get('http://localhost:8083/profileMicroservice/api/profile/getUserProfile/'+ this.$route.params.selectedUsername)
            .then(response => {
               this.user = response.data
            }).catch(res => {
                        alert("Error");
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
                    
        
   },
    methods:{
        showHomepage: function(){
           window.location.href = "/homepage";
        },
        
    }   
}
</script>
<style scoped>
    .image_style{
        margin-right:15%;
        margin-left:45%;
        margin-bottom: 4%;
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
        margin-right:15%;
        margin-left:30%;
        margin-top: 4%;
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
        width: 40%;
        height: 120%;
        margin-left: 30%;
        margin-top: 0%;
        margin-bottom: 0.1%;
    }
    .header_look{
        background: #d4bcce; 
        width: 40%;
        height: 120%;
        margin-left: 30%;
        margin-bottom: 0.1%;
        margin-top: 0.5%;
    }
</style>