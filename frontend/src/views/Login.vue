

<template>
<div id="login">
     <div class="vue-tempalte">
        
            <h3>Sign In</h3>

            <div class="form-group">
                <label style="color:white">Username</label>
                <input type="email" class="form-control form-control-lg" required />
                <label style="color:white">Password</label>
                <input type="password" class="form-control form-control-lg" required />
            </div>

            
            <button style="color:white" type="submit" class="button" v-on:click = "loginUser">Sign In</button>

            

       
    </div>
  
</div>   
</template>

<script>
export default {
    name: 'Login',
     data() {
    return {
       promotions :[],
     
    }
  },
  methods:{
       
  loginUser : function() {
           
            this.axios.post('http://localhost:8083/profileMicroservice/api/auth/login').then(response => {
                    alert("ok.");
                    
                    console.log(response); 
                }).catch(res => {
                       alert("Please try later.");
                        console.log(res);
                });
      },
       
    
  }
}
</script>

<style scoped>
.vue-tempalte{
  width: 100%;
  height: 100%;
  background:  #f6f9fa!important;
  min-height: 100vh;
  display: flex;
  font-weight: 400;
  justify-content: center;
  flex-direction: column;   
    }

.form-group{
  position: relative;
  z-index: 1;
  background:  #174452;
  max-width: 400px;
  margin: 0 auto 100px;
  padding: 65px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  
    }
.button {
   background:  #174452;
   box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
   border:  1px solid rgb(25, 40, 90); 
   padding: 10px 20px;
   position: absolute;
   top: 80%;
   right: 40%;
   width: 20%;
   height: 7%;
}


.homepage_style{
        background: #17a2b8; 
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        height: 70px;
    }

    .space_style{
        margin-right:5px
    }

</style>

