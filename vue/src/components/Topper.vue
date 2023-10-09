<template>
  <div id="topper">
    <!-- logo area -->
    <div id="topArea" class="topArea">
      <div id="logo">
        <div id="logo">
          <span id="p">p</span>
          <span id="i">i</span>
          <span id="n">n</span>
          <span id="go">go</span>
          <span id="exclamation">!</span>
        </div>
      </div>

      <!-- search bar area -->
      <div id="searchBar" class="navbar-toggle" @click="mainNavToggle">
        <form>
          <div>
            <input
              type="text"
              placeholder="starting from?"
              onfocus="this.placeholder=''"
              onblur="this.placeholder='starting from?'"
            />
          </div>
        </form>
      </div>

<!-- miles slider -->
      <div class="slidecontainer" v-if="showAttractions">
          <input
            type="range"
            min="1"
            max="30"
            value="50"
            class="slider"
            id="myRange"
          />
        </div>
      <!-- attraction type buttons  -->
      <div class="attractionButtonContainer" v-if="showAttractions">
        <button class="attractionButton">museum</button>
        <button class="attractionButton">restaurant</button>
        <button class="attractionButton">cafe</button>
        <button class="attractionButton">park</button>
      </div>

      <!-- my small photo linking to my account dropdown -->
      <nav>
        <img
          src="../assets/person.jpg"
          id="userPic"
          @click="showDropdown"
          class="userOn"
          v-if="loggedOut"
        />
        <img
          src="../assets/samantha.png"
          id="userPic"
          @click="showDropdown"
          class="userOn"
          v-if="loggedIn"
        />

        <!-- my account menu LOGGED IN-->
        <div id="menu" v-if="showMenu">
          <div class="menu">
            <div class="accountDropdownHeader">
              <h3 id="userName" v-if="loggedOut">welcome!</h3>
              <h3 id="userName" v-if="loggedIn">Sam Doe</h3>
              <div id="changePhoto">
                <img
                  src="../assets/samantha.png"
                  id="largeUserPic"
                  v-if="loggedIn"
                />
                <img
                  src="../assets/person.jpg"
                  id="largeUserPic"
                  v-if="loggedOut"
                />
              </div>
            </div>
            <hr />

            <a href="Account" class="menuLink" v-if="loggedIn">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>edit profile</p>
              <span>></span>
            </a>

            <a href="MyToursPage" class="menuLink" v-if="loggedIn">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>my trips</p>
              <span>></span>
            </a>
            <a href="/" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>new trip</p>
              <span>></span>
            </a>

            <a
              @click="logout"
              class="menuLink"
              v-if="loggedIn"
              id="loginLogout"
            >
              <p>logout</p>
              <span>></span>
            </a>
            <a
              @click="login"
              class="menuLink"
              v-if="loggedOut"
              id="loginLogout"
            >
              <p>login</p>
              <span>></span>
            </a>
          </div>
        </div>
      </nav>
    </div>
  </div>
</template>

<script>
import userIcon from "../assets/userIcon.vue";
// import magnifyingGlass from "../assets/magnifyingGlass.vue";
// import FilterHamburger from "../assets/filterHamburger.vue";

export default {
  components: { userIcon },
  name: "Topper",
  data: function () {
    return {
      showMenu: false,
      loggedIn: false,
      loggedOut: true,
      showAttractions: false,
      showLargeBackground: false,
    };
  },
  props: {
    onClick: { type: Function },
  },
  methods: {
    showDropdown: function () {
      this.showMenu = !this.showMenu;
    },

    mainNavToggle: function () {
      //hide and show the attraction buttons and slider
      this.showAttractions = !this.showAttractions;

      if (this.showLargeBackground) {
        document.getElementById("topArea").style.height = "170px";
        this.showLargeBackground = false;
      } else if (!this.showLargeBackground) {
        document.getElementById("topArea").style.height = "300px";
        this.showLargeBackground = true;
      }
    },
    logout: function () {
      this.loggedOut = !this.loggedOut;
      this.loggedIn = !this.loggedIn;
    },
    login: function () {
      this.loggedIn = !this.loggedIn;
      this.loggedOut = !this.loggedOut;
    },
  },

  // Add a click event to run the mainNavToggle function
};
</script>

<style scoped>
.slideContainer{
  margin-top: 49px;
}
#myRange{
  display: flex;
  position: absolute;
  width: 320px;
  left: 50%;
  margin-left: -180px;
  background: transparent;
  box-shadow: none;
  bottom: 80px;
}
.attractionButtonContainer {
  display: flex;
  position: absolute;
  justify-content: center;
  width: 400px;
  left: 50%;
  margin-left: -190px;
  margin-top: 230px;
}
.attractionButton {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  line-height: 20px;
  letter-spacing: 1.8px;
  background: transparent;
  border: none;
}
#p {
  color: #adff00;
}
#i {
  color: #adff00;
}
#n {
  color: #cfe7ca;
}
#go {
  color: #cfe7ca;
}
#exclamation {
  color: #cfe7ca;
}

/* #filterGo {
  display: flex;
  justify-content: space-between;
  width: 100%;
} */

/* #filter {
  flex: 8;
  padding-left: 26px;
}
#go {
  flex: 0;
  padding-right: 26px;
} */
/* form{
  display: flex;
} */
#searchBar {
  margin: auto;
  margin-top: 120px;
}
input {
  height: 30px;
  padding: 0px 16px;
  border-radius: 20px;
  background: #173951;
  box-shadow: 0px 4px 3px 0px rgba(3, 36, 6, 0.6) inset;
  width: 50vw;
  max-width: 400px;
  bottom: 0px;
  border: none;
}

::placeholder {
  color: var(--menu-bar-mint-50, rgba(207, 231, 202, 0.5));
  font-family: Inter;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px;
  letter-spacing: 1.8px;
}

#changePhoto {
  display: flex;
  z-index: 2;
}

#largeUserPic {
  border-radius: 50%;
  width: 80px;
  height: 80px;
  margin-right: 15px;
}

#topper {
  position: fixed;
  top: 0;
  z-index: 100;
  width: 100%;
}
#topArea {
  border-radius: 0px 0px 20px 20px;
  background-image: linear-gradient(
    180deg,
    #182935 0%,
    #182c3a 36.46%,
    #183143 54.69%,
    #183c55 75.52%,
    #194f77 97.92%
  );
  height: 170px;
  display: flex;
  position: relative;
}

#logo {
  width: 100%;
  color: var(--mint, #cfe7ca);
  text-shadow: 0px -4px 4px rgba(0, 0, 0, 0.3);
  font-family: Orelega One;
  font-size: 48px;
  text-decoration: none;
  margin-top: 26px;
  position: absolute;
  text-align: center;
}
.userOff {
  display: none;
}
.userOn {
  display: block;
}

#userPic {
  overflow: hidden;
  border-radius: 50%;
  width: 26px;
  height: 26px;
  object-fit: cover;
  border: 2px solid var(--mint, #cfe7ca);
  margin-top: 110px;
  z-index: 3;
  position: absolute;
  right: 26px;
}

.menu {
  position: absolute;
  top: 100%;
  right: 5%;
  width: 380px;
  max-height: 600px;
  overflow: hidden;
  transition: max-height 0.5s;
  z-index: 6;
}
/* .menu.openMenu {
  max-height: 600px;
} */
.menu {
  background: #182935;
  padding: 20px;
  margin: 10px;
  border-radius: 20px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.4);
}
.accountDropdownHeader {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}
#userName {
  color: var(--mint, #cfe7ca);
  text-align: center;
  font-family: Orelega One;
  font-size: 30px;
  font-weight: 400;
  letter-spacing: 1px;
  text-transform: lowercase;
  margin: 0px;
}
.menu hr {
  border: 0;
  height: 1px;
  width: 100%;
  background: #cfe7ca85;
  margin: 15px 0 10px;
}
.menuLink {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #cfe7ca85;
  margin: 12px 0px;
  font-family: Inter;
  font-size: smaller;
}
.menuLink p {
  width: 100%;
}
.menuLink:hover p {
  /* width: 100%; */
  font-weight: 500;
  color: #cfe7ca;
}
.menuLinkIcon {
  margin-left: 10px;
  margin-right: 15px;
}
.menuLink span {
  transition: transform 0.5s;
}
.menuLink:hover span {
  transform: translateX(5px);
  color: #cfe7ca;
}
#loginLogout {
  font-size: 18px;
  text-align: center;
}
</style>

