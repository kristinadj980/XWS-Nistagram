<template>
<div id="registration">
   
    <div class="vue-tempalte">
       
            <h3>Sign Up</h3>
            <div class="form-group" style="width:50%;">
                 
                 <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Name</label>
                        <input type="text" class="form-control" v-model="name" placeholder="Enter name" required>
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Surname</label>
                        <input type="text" class="form-control" v-model = "surname" placeholder="Enter surname" required >
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Email</label>
                        <input type="email" class="form-control" v-model="email" placeholder="Enter email" required>
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Phone number</label>
                        <input type="number" class="form-control" v-model="phone" placeholder="Enter phone number" required>
                        </div>
                    </div>
                      <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Username</label>
                        <input type="text" class="form-control" v-model="username" placeholder="Enter username" required>
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Birth Date</label>
                        <input type="date" class="form-control" v-model="birthDate" placeholder="Enter birth date" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                        <label style="color:white">Password</label>
                        <input type="password" class="form-control" v-model="password" placeholder="Enter password" required>
                        </div>
                        <div class="form-group col-md-6">
                        <label style="color:white">Confirm password</label>
                        <input type="password" class="form-control" v-model="passwordConf" placeholder="Repeat password" required>
                        </div>
                    </div>
                </div>
            <button style="color:white; margin-bottom: 40%;" type="submit" class="button" v-on:click = "registerUser">Sign Up</button>
            
        
    </div>
</div>
</template>

<script>
export default {
    name: 'Registration',
    data(){
        return{
            email:'',
            password:'',
            passwordConf:'',
            name:'',
            surname:'',
            street:'',
            streetNumber:'',
            city:'',
            country:'',
            phone:'',
            birthDate: '',
            gender: 'male',
            username: '',
        }

    },
    methods:{
         registerUser: function(){
           
            const userInfo ={
                email : this.email,
                password : this.password,
                confirmPassword : this.passwordConf,
                username : this.username,
                name : this.name,
                surname : this.surname,
                phoneNumber : this.phone,
                birthDate :this.birthDate,
                gender : this.gender,
            }
            this.axios.post('http://localhost:8083/profileMicroservice/api/auth/register',userInfo)
                .then(response => {
                        this.$router.push('/login') 
                        console.log(response.data);
                })
                .catch(response => {
                    console.log(response.data)
                    alert("User with that username already exist! Choose another.")
                   // alert(response.response.data.message);
                 });    
      
        }
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
    max-width: 900px;
    margin: 0 auto 20px;
    padding: 10px;
    text-align: center;
    margin: auto;
    
        }

    .button {
    background:  #692d5a;
    box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    border:  1px solid rgb(25, 40, 90); 
    padding: 10px 20px;
    position: absolute;
    top: 110%;
    right: 40%;
    width: 20%;
    height: 7%;
    margin-bottom: 40%;
    }

    .homepage_style{
        background: #692d5a; 
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