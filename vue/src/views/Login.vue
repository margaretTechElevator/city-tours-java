<template>
  <body>
    <div id="login">
      <navigation-bar-hamburger/>
      <form @submit.prevent="login">
        <!-- <h1 >City Tours</h1>
      <h3>lets plan the perfect daytrip</h3> -->
        <div id="usernameAndPassword">
          <h2>please sign in</h2>
          <div role="alert" v-if="invalidCredentials">
            Invalid username and password!
          </div>
          <div role="alert" v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
          </div>

          <div class="form-input-group">
            <!-- <label for="username">Username</label> -->
            <!-- placeholder="username(email)" -->
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
            <!-- <label for="password">Password</label> -->
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
// import Navigation from "../components/navigation.vue";
import NavigationBarHamburger from '../components/navigationBarHamburger.vue';
import authService from "../services/AuthService";

export default {
  name: "login",
  components: { NavigationBarHamburger },
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
  background-image: url("https://i.ibb.co/svhYTX5/city.png");
  /* background-image: url("https://images.macrumors.com/t/uqbExVV-DT2ShQO9B5ouXYw3z7c=/800x0/article-new/2016/06/thumb-1-800x264.jpeg?lossy"); */
}
button {
  background-color: rgba(255, 255, 255, 0.74);
  border: none;
  text-align: center;
  padding-top: 8px;
  padding-bottom: 8px;
  color: #6b1717;
  font-family: "Gill Sans", "Gill Sans MT", Calibri, "Trebuchet MS", sans-serif;
  font-weight: 900;
  font-size: 1rem;
  line-height: 1;
  text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
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
  border-color: transparent;
}
#login {
  text-align: center;
  height: 700px;
  /* background-image: url("https://i.ibb.co/bLCxN8R/vecteezy-young-woman-tourist-looking-at-map-planning-trip-happy-girl-13215197.jpg"); */
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
  text-shadow: 1px 1px 10px rgba(130, 114, 110, 0.5);
}
#usernameAndPassword {
  margin: auto;
  width: 30%;
  background-color: rgba(255, 255, 255, 0.5);
  padding: 50px;
}
</style>