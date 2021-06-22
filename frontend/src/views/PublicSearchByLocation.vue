<template>
  <div id="publicSearchByLocation">
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
  </div>
</template>

<script>
export default {
    name: 'PublicSearchByLocation',
    data() {
    return {
        searchData: "",
        profile: [],
        username: "",
        posts: [],
        selectedUser:[''],
        choosenLocation:'',
        user:'',
        videoText: "mp4",
        likesNumber:0,
        dislikesNumber:0,
        loggeduser:'',
        fileNames:[],
        fileName:'',
        postsNumber: 0,
        }
    },
    async mounted(){
        this.choosenLocation = this.$route.params.selectedLocation;
        console.log(this.choosenLocation);
         this.axios.get('http://localhost:8083/searchMicroservice/location/findPostsByLocation/'+ this.choosenLocation)
            .then(response => {
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
                        alert("Posts with this location does not exists!");
                            console.log(res);
                    });
    }
}
</script>

<style>

</style>