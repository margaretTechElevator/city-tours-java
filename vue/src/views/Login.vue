<template>
  <body>
    <div id="login">
      <Navigation />

      <form @submit.prevent="login">

        <div id="usernameAndPassword">
          <h2>please sign in</h2>
          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>

          <div class="form-input-group">

            <input
              type="text"
              placeholder="email"
              class="inputs"
              id="username"
              v-model="user.username"
              required
              autofocus
            />
          </div>
          <div class="form-input-group">
            
            <input
              type="password"
              placeholder="password"
              class="inputs"
              id="password"
              v-model="user.password"
              required
            />
          </div>
          <button type="submit">log in</button>
          <p></p>
          <button @click="$router.push('/register')">join us</button>
        </div>
      </form>
    </div>
  </body>
</template>

<script>
  import Navigation from '../components/Navigation.vue';
  import authService from "../services/AuthService";

  export default {
    name: "Login",
    components: { Navigation },
    data() {
      return {
        user: {
          username: "",
          password: "",
        },
        invalidCredentials: false,
      };
    },
    methods: {
      login() {
        authService
          .login(this.user)
          .then((response) => {
            if (response.status == 200) {
              this.$store.commit("SET_AUTH_TOKEN", response.data.token);
              this.$store.commit("SET_USER", response.data.user);
              this.$router.push("/");
            }
          })
          .catch((error) => {
            const response = error.response;

            if (response.status === 401) {
              this.invalidCredentials = true;
            }
          });
      },
    },
  };
</script>

<style scoped>
  body {
    background-image: url("https://i.ibb.co/GWwykcL/chi.png");
  }

  button {
    background-color: rgb(236, 191, 93);
    border: none;
    text-align: center;
    padding-top: 8px;
    padding-bottom: 8px;
    color: #6b1717;
    font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
    font-weight: 900;
    font-size: 1rem;
    line-height: 1;
    box-shadow: 1px 1px 10px rgba(130, 114, 110, 0.186);
  }

  ::placeholder {
    color: #e0a788e0;
    font-weight: 900;
    letter-spacing: 0.15rem;
    font-size: 0.75rem;
    background: transparent;
    font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  }

  .form-input-group {
    margin-bottom: 1rem;
    margin-left: 1rem;
    margin-right: 1rem;
    text-align: center;
    color: #dd5a13;
    font-weight: 400;
    letter-spacing: 0.1rem;
    background: transparent;
    font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  }

  .inputs {
    text-align: center;
    width: 100%;
    box-shadow: 1px 1px 10px rgba(255, 255, 255, 0.36);
    border:rgb(203, 203, 203) 0.5px solid;
    background-color: rgba(158, 158, 158, 0.248);
  }
  
  #login {
    text-align: center;
    height: 700px;
  }

  h2 {
    text-align: center;
    padding-top: 10px;
    padding-bottom: 15px;
    color: #6b1717;
    font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
    font-weight: 900;
    font-size: 1.5rem;
    line-height: 0;
    text-shadow: 1px 1px 10px rgba(130, 114, 110, 0);
  }

  #usernameAndPassword {
    margin-top:50px;
    margin-left: auto;
    margin-right:auto;
    width: 30%;
    background-color: rgb(255, 255, 255);
    padding: 50px;
  }
</style>