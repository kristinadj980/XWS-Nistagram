<template>
  <div>
    <nav class="navbar navbar-default" role="navigation" aria-label="main navigation">
      <div class="navbar-brand"
      ></div>
        <div id="navbar" class="navbar-menu">
          <div class="navbar-end">
            <div class="navbar-item">
              <img class="image_style space_style" title="Nistagram" style="width: 50px; height: 50px; left:10px; margin-top:-5px;"
                  src="http://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c521.png">
              <a class="button is-dark"  href="http://localhost:8081/">
                <strong>Home</strong>
              </a>    
              <a class="button is-dark"  href="/login">
                <strong>LogIn</strong>
              </a>
              <a class="button is-dark"  href="/registration">
                <strong>Register</strong>
              </a>
              
          </div>
        </div>
      </div>
    </nav>
    <public-search v-if="isMainPage"  style="margin-left:38%; height: 50px; width:25%;left:10px; margin-bottom:2%;"></public-search>
    <hr v-if="isMainPage">
    <public-homepage v-if="isMainPage" style="post_look"></public-homepage>
  </div>
</template>

<script>
import PublicHomepage from './PublicHomepage.vue'
import PublicSearch from './PublicSearch.vue'
export default {
  components: { PublicHomepage, PublicSearch },
    name: 'Nav',
    computed: {
    isMainPage() {
      console.log(this.$route.name)
      return this.$route.name === 'Home'
    }
}
}
</script>
<style scoped>
 nav {
    margin-top: 5px;
    margin-bottom: 5px;
    background-color: #f5f1f4;
    
  } 
  .navbar a {
  float: left;
  padding: 10px;
  color: rgb(82, 25, 90);
  text-decoration: none;
  font-size: 17px;
  
}
.navbar a:hover {
  background-color: rgb(164, 198, 221);
}
.image_style{
  height: 400px;
  width: 60%;
}
.space_style{
  margin-right:15px;
  margin-left:10px;
}


</style>