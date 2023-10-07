<template>
  <div id="topper">
    <div id="topArea">
      <div id="logo">
        <div id="logo">
          <span id="p">p</span>
          <span id="i">i</span>
          <span id="n">n</span>
          <span id="go">go</span>
          <span id="exclamation">!</span>
        </div>
      </div>

      <div id="searchBar">
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

      <!-- dropdown menu -->
      <div class="toggle">
        <!-- toggle on click small user photo -->
        <img src="../assets/samantha.png" id="userPic" />
        <!-- <img src="../assets/samantha.png" id="userPic" onclick="toggleMenu()"/> -->

        <div class="menuWrap" id="menu">
          <div class="menu">
            <div class="userInfo">
              <h3 id="userName">Sam Doe</h3>
              <div id="changePhoto">
                <img src="../assets/samantha.png" id="largeUserPic" />
                <!-- <div id="addContainer">
                  <plusIcon/>
                </div> -->
              </div>
            </div>
            <hr />

            <a href="#" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>login/logout</p>
              <span>></span>
            </a>

            <a href="Account" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>edit profile</p>
              <span>></span>
            </a>

            <a href="MyToursPage" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>my trips</p>
              <span>></span>
            </a>
            <a href="/" class="menuLink">
              <div class="menuLinkIcon"><userIcon /></div>
              <p>new trip</p>
              <span>></span>
            </a>
          </div>
        </div>
      </div>
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
};

// this isnt working.   error  'toggleMenu' is defined but never used but it
// IS used in the img.  ?

// function toggleMenu() {

//(function toggle A)
//   // let menu= document.getElementById("menu");
//   // menu.classList.toggle("openMenu");

//(function toggle B)
//   var x = document.getElementById("menu");
//   if (x.style.display === "none") {
//     x.style.display = "block";
//   } else {
//     x.style.display = "none";
//   }
// }

// Close menu when clicking outside
</script>

<style scoped>
#p {
  color: #adff00;
}
#i {
  color: #adff00;
}
#n {
  color:#cfe7ca;
}
#go {
  color:#cfe7ca;
}
#exclamation {
  color:#cfe7ca;
}
/* #goButton {
  color: var(--selected-green, #adff00);
  font-family: Orelega One;
  font-size: 30px;
  letter-spacing: 1px;
  background-color: transparent;
  border: none;
} */
/* #magnifyingGlass {
  position: absolute;
  margin-left: 400px;
  margin-top: 10px;
} */
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
  background:#173951;
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
  /* padding-left: 20px; */
}

/* toggle display on and off til the function is fixed */
.toggle {
  /* display: none; */
}
#changePhoto {
  display: flex;
  z-index: 2;
}

#largeUserPic {
  border-radius: 50%;
  width: 80px;
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
  /* padding: 17px 16px; */
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
.userInfo {
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
</style>

