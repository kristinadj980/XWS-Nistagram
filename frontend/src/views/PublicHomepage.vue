<template>
  <div id="publicHomepage">

    <b-card class="post_look" v-for="post in posts" colu v-bind:key="post.fileName">
      <b-row >
        <h4 align="left"  v-b-modal.modal-4><b-icon icon="person-circle" aria-hidden="true"></b-icon>  {{post.username}}</h4>
      </b-row>
      <h6 align="left"  v-b-modal.modal-4>{{post.locationDTO.city}},{{post.locationDTO.street}},{{post.locationDTO.objectName}},{{post.locationDTO.country}}</h6>
      <div v-for="(image, index) in post.images" v-bind:key="image.imageBytes"  v-b-modal.modal-4>
        <b-img v-if="!post.fileNames[index].includes(videoText)" thumbnail  v-bind:src="image.imageBytes" width="400" height="400"  alt="Image 1"></b-img>
        <video v-if="post.fileNames[index].includes(videoText)" controls v-bind:src="image.imageBytes" width="400" height="400" style="display:block; margin-left:auto; margin-right:auto"></video>
      </div>
      <h4 align="left" style="margin-top:5px;"  v-b-modal.modal-4>{{post.description}}</h4>
      <h5 align="left"  v-b-modal.modal-4>
        <span v-for="(tag,t) in post.tags" :key="t">
          #{{tag.name}}
        </span>
      </h5>
      <h5 align="left"><b-icon icon="hand-thumbs-up" aria-hidden="true"  v-b-modal.modal-4></b-icon>{{post.numberOfLikes}}  likes
      <b-icon icon="hand-thumbs-down" aria-hidden="true"  v-b-modal.modal-4></b-icon>{{post.numberOfDislikes}} dislikes  </h5>
      <h4 align="left"  v-b-modal.modal-4><b-icon icon="chat-square" aria-hidden="true"  v-b-modal.modal-4></b-icon> {{post.numberOfComments}}  comments </h4>              
    </b-card>
    <b-modal ref="modal-ref4" id="modal-4"  hide-footer>
      <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; left:10px;"
                  src="http://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c521.png">
      <h3 align="center">Please register if you want to see more! </h3>
      <b-button class="btn btn-info btn-lg space_style" style="background-color:#f08080; width:5cm;" v-on:click = "register">Register</b-button>
                                        
    </b-modal>
  </div>
  
</template>

<script>
export default {
    name: 'publicHomepage',
    data() {
    return {
        posts:[],
        }
    },
    mounted(){
      this.axios.get('http://localhost:8083/mediaMicroservice/post/getPublicProfilesPosts'
       ).then(response => {
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
            console.log(res);
      });
       
   },methods:{
     register: function(){
           window.location.href = "/registration";
        },
   }
}
</script>

<style>

  .post_look {
    background: #fcf6f6; 
    width: 40%;
    height: 120%;
    margin-left: 30%;
    margin-bottom: 4%;
    margin-top: 4%;
  }

  .space_style{
    margin-right:15%;
    margin-left:30%;
    margin-top: 4%;
  }

  .image_style{
    margin-right:15%;
    margin-left:45%;
    margin-bottom: 4%;
  }

</style>