<template>
<nav class="navbar navbar-default" role="navigation" aria-label="main navigation">
  <div class="navbar-brand">
  </div>
  <div id="navbar" class="navbar-menu">
    <div class="navbar-end">
      <div class="navbar-item">
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
</template>
<script>
export default {
    name: 'Nav'
}
</script>
<style scoped>
 nav {
    margin-top: 5px;
    margin-bottom: 5px;
    background-color: #17a2b8;
  } 
  .navbar a {
  float: left;
  padding: 10px;
  color: rgb(29, 4, 167);
  text-decoration: none;
  font-size: 17px;
}
.navbar a:hover {
  background-color: rgb(164, 198, 221);
}
  
</style>