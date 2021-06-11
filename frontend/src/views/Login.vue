

<template>
<div id="login">
     <div class="vue-tempalte">
        
            <h3>Sign In</h3>

            <div class="form-group">
                <label style="color:white">Username</label>
                <input type="username" class="form-control form-control-lg" v-model="username" required />
                <label style="color:white">Password</label>
                <input type="password" class="form-control form-control-lg" required v-model="password"/>
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
       password:'',
       username:'',
       authority:""
     
    }
  },
  methods:{
       
  loginUser : function() {
  const loginInfo ={
                username : this.username,
                password : this.password,
            }
            localStorage.removeItem('token');

            this.axios.post('http://localhost:8083/profileMicroservice/api/auth/login',loginInfo)
                .then(response => {
                    localStorage.setItem('token', JSON.stringify(response.data.accessToken));
                    let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
                    this.axios.get('http://localhost:8083/profileMicroservice/api/auth/authority',{  
                         headers: {
                                'Authorization': 'Bearer ' + token,
                        }
                    }).then(response => {
                        
                            this.authority = response.data.authorities[0].authority;
                            alert("OK?")
                            if(this.authority==="ROLE_REGISTRED_USER") {
                                  window.location.href = '/homepage';
                                }
                        
                           
                            else alert("Error has occured."); 

                    }).catch(res => {
                                alert("NOT OK");
                                 
                                    console.log(res);
                            });

                            })
                .catch(response => {
                       alert("Please enter valid data!");
                        console.log(response);
                 });   
        },  
    
  }
}
</script>

<style scoped>
.vue-tempalte{
  width: 100%;
  height: 100%;
  background:  #f5f1f4!important;
  min-height: 100vh;
  display: flex;
  font-weight: 400;
  justify-content: center;
  flex-direction: column;   
    }

.form-group{
  position: relative;
  z-index: 1;
  background:  #692d5a;
  max-width: 400px;
  margin: 0 auto 100px;
  padding: 65px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
  
    }
.button {
   background:  #692d5a;
   box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
   border:  1px solid rgb(82, 25, 90); 
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

