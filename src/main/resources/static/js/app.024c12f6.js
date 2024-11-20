(function(){"use strict";var e={9636:function(e,t,o){var r=o(5130),s=o(6768);const a={id:"app"},n={class:"header"},i={class:"logo"},u={class:"auth-buttons"},l={key:0},d={key:1},c={key:0,class:"default-content"},m={class:"image-container"},p=["src"],h=["src"];function g(e,t,g,b,k,f){const v=(0,s.g2)("router-link"),L=(0,s.g2)("router-view");return(0,s.uX)(),(0,s.CE)("div",a,[(0,s.Lk)("div",n,[(0,s.Lk)("div",i,[(0,s.bF)(v,{to:"/",class:"logo-text"},{default:(0,s.k6)((()=>t[3]||(t[3]=[(0,s.eW)("EveryHealth")]))),_:1})])]),(0,s.Lk)("div",u,[k.isLoggedIn?(0,s.Q3)("",!0):((0,s.uX)(),(0,s.CE)("div",l,[(0,s.bF)(v,{to:"/users/login",class:"auth-button",onClick:(0,r.D$)(f.handleLoginClick,["prevent"])},{default:(0,s.k6)((()=>t[4]||(t[4]=[(0,s.eW)("Log in")]))),_:1},8,["onClick"]),(0,s.bF)(v,{to:"/users/save",class:"auth-button",onClick:(0,r.D$)(f.handleRegisterClick,["prevent"])},{default:(0,s.k6)((()=>t[5]||(t[5]=[(0,s.eW)("Register")]))),_:1},8,["onClick"])])),k.isLoggedIn?((0,s.uX)(),(0,s.CE)("div",d,[(0,s.Lk)("nav",null,[(0,s.bF)(v,{to:f.myPageLink,class:"nav-link",style:{"margin-right":"20px"}},{default:(0,s.k6)((()=>t[6]||(t[6]=[(0,s.eW)("Mypage")]))),_:1},8,["to"]),(0,s.bF)(v,{to:"/board",class:"nav-link",style:{"margin-right":"20px"}},{default:(0,s.k6)((()=>t[7]||(t[7]=[(0,s.eW)("Community")]))),_:1}),(0,s.Lk)("button",{onClick:t[0]||(t[0]=(...e)=>f.logout&&f.logout(...e)),class:"auth-button"},"Log out")])])):(0,s.Q3)("",!0)]),k.isLoggedIn||!k.isLoggedIn&&!k.isNavigating&&!k.isLoginPage?((0,s.uX)(),(0,s.CE)("div",c,[(0,s.Lk)("div",m,[(0,s.Lk)("img",{src:o(9928),alt:"Image 1",class:"default-image"},null,8,p),(0,s.Lk)("img",{src:o(855),alt:"Image 2",class:"default-image"},null,8,h)]),t[8]||(t[8]=(0,s.Lk)("p",{class:"image-caption"},[(0,s.eW)(" Welcome to EveryHealth!"),(0,s.Lk)("br"),(0,s.Lk)("br"),(0,s.eW)(" EveryHealth is your all-in-one health management app designed to seamlessly integrate your diet and exercise routines."),(0,s.Lk)("br"),(0,s.eW)(" With our intuitive platform, you can easily log your meals and workouts, track your progress, and receive personalized workout recommendations based on your unique fitness data."),(0,s.Lk)("br"),(0,s.Lk)("br"),(0,s.eW)(" Connect with a community of fitness enthusiasts through our interactive bulletin board, where you can share your achievements, motivate each other, and celebrate your journeys together."),(0,s.Lk)("br"),(0,s.eW)(" Whether you're aiming to lose weight, build muscle, or maintain a healthy lifestyle, FitConnect is here to support you every step of the way."),(0,s.Lk)("br"),(0,s.Lk)("br"),(0,s.eW)(" Join us in transforming your health and fitness experience!"),(0,s.Lk)("br"),(0,s.Lk)("br"),(0,s.eW)(" Feel free to modify any part to better fit your vision! ")],-1))])):(0,s.Q3)("",!0),(0,s.bF)(L,{onNavigationStart:t[1]||(t[1]=e=>k.isNavigating=!0),onNavigationEnd:t[2]||(t[2]=e=>k.isNavigating=!1)})])}o(4114);var b={name:"App",data(){return{isLoggedIn:!1,isNavigating:!1,isLoginPage:!1}},mounted(){this.checkLoginStatus(),this.checkIfLoginPage()},computed:{myPageLink(){const e=localStorage.getItem("user_id"),t=this.getTodayDate();return e?`/myPage/${e}/${t}`:"/"}},methods:{checkLoginStatus(){const e=localStorage.getItem("token");this.isLoggedIn=!!e},checkIfLoginPage(){this.isLoginPage="/users/login"===this.$route.path||"/users/save"===this.$route.path},handleLoginClick(){this.isNavigating=!0,this.$router.push("/users/login")},handleRegisterClick(){this.isNavigating=!0,this.$router.push("/users/save")},logout(){localStorage.removeItem("token"),this.isLoggedIn=!1,this.isNavigating=!1,this.$router.push("/")},getTodayDate(){const e=new Date,t=e.getFullYear(),o=("0"+(e.getMonth()+1)).slice(-2),r=("0"+e.getDate()).slice(-2);return`${t}-${o}-${r}`}},watch:{$route:function(){this.checkLoginStatus(),this.checkIfLoginPage()}}},k=o(1241);const f=(0,k.A)(b,[["render",g]]);var v=f,L=o(1387);function y(e,t){return(0,s.uX)(),(0,s.CE)("div")}const w={},F=(0,k.A)(w,[["render",y]]);var P=F;const N={class:"container"},$={class:"input-group"},C={class:"input-group phone-group"},E={class:"input-group"},x={class:"input-group"},I={class:"input-group"};function S(e,t,o,a,n,i){return(0,s.uX)(),(0,s.CE)("div",N,[t[12]||(t[12]=(0,s.Lk)("h2",{class:"register-title"},"Register",-1)),(0,s.Lk)("form",{onSubmit:t[5]||(t[5]=(0,r.D$)(((...e)=>i.submitForm&&i.submitForm(...e)),["prevent"])),class:"register-form"},[(0,s.Lk)("div",$,[t[6]||(t[6]=(0,s.Lk)("label",{for:"email"},"Email :",-1)),(0,s.bo)((0,s.Lk)("input",{type:"email",id:"email","onUpdate:modelValue":t[0]||(t[0]=e=>n.userForm.email=e),required:""},null,512),[[r.Jo,n.userForm.email]])]),(0,s.Lk)("div",C,[t[7]||(t[7]=(0,s.Lk)("label",{for:"phoneNumber"},"Phone Number :",-1)),(0,s.bo)((0,s.Lk)("input",{type:"text",id:"phoneNumber","onUpdate:modelValue":t[1]||(t[1]=e=>n.userForm.phoneNumber=e),required:""},null,512),[[r.Jo,n.userForm.phoneNumber]])]),(0,s.Lk)("div",E,[t[8]||(t[8]=(0,s.Lk)("label",{for:"name"},"Username :",-1)),(0,s.bo)((0,s.Lk)("input",{type:"text",id:"name","onUpdate:modelValue":t[2]||(t[2]=e=>n.userForm.name=e),required:""},null,512),[[r.Jo,n.userForm.name]])]),(0,s.Lk)("div",x,[t[9]||(t[9]=(0,s.Lk)("label",{for:"nickname"},"Nickname :",-1)),(0,s.bo)((0,s.Lk)("input",{type:"text",id:"nickname","onUpdate:modelValue":t[3]||(t[3]=e=>n.userForm.nickname=e),required:""},null,512),[[r.Jo,n.userForm.nickname]])]),(0,s.Lk)("div",I,[t[10]||(t[10]=(0,s.Lk)("label",{for:"password"},"Password (over 8) :",-1)),(0,s.bo)((0,s.Lk)("input",{type:"password",id:"password","onUpdate:modelValue":t[4]||(t[4]=e=>n.userForm.password=e),required:""},null,512),[[r.Jo,n.userForm.password]])]),t[11]||(t[11]=(0,s.Lk)("button",{type:"submit",class:"submit-button"},"Join us",-1))],32)])}var _={data(){return{userForm:{email:"",phoneNumber:"",name:"",nickname:"",password:""}}},methods:{validatePhoneNumber(e){const t=/^[0-9]+$/;return t.test(e)},validatePassword(e){return e.length>=8},submitForm(){if(!this.validatePhoneNumber(this.userForm.phoneNumber))return void alert("전화번호는 숫자만 입력 가능합니다.");if(!this.validatePassword(this.userForm.password))return void alert("비밀번호는 최소 8자 이상이어야 합니다.");const e={email:this.userForm.email,phoneNumber:this.userForm.phoneNumber,name:this.userForm.name,nickname:this.userForm.nickname,password:this.userForm.password};this.$axios.post("/users/save",e).then((e=>{console.log("회원가입 성공:",e.data),this.$router.push("/")})).catch((e=>{e.response&&400==e.response.status?alert("이미 존재하는 이메일입니다."):alert("회원가입 중 오류가 발생했습니다.")}))}}};const W=(0,k.A)(_,[["render",S]]);var A=W;const J={class:"container"},O={class:"input-group"},j={class:"input-group"},U={class:"button-container"};function X(e,t,o,a,n,i){const u=(0,s.g2)("router-link");return(0,s.uX)(),(0,s.CE)("div",J,[t[8]||(t[8]=(0,s.Lk)("h1",{class:"login-title"},"Join us",-1)),(0,s.Lk)("form",{onSubmit:t[2]||(t[2]=(0,r.D$)(((...e)=>i.login&&i.login(...e)),["prevent"])),class:"login-form"},[(0,s.Lk)("div",O,[t[3]||(t[3]=(0,s.Lk)("label",{for:"email"},"Email: ",-1)),(0,s.bo)((0,s.Lk)("input",{type:"email","onUpdate:modelValue":t[0]||(t[0]=e=>n.email=e),id:"email"},null,512),[[r.Jo,n.email]])]),(0,s.Lk)("div",j,[t[4]||(t[4]=(0,s.Lk)("label",{for:"password"},"Password: ",-1)),(0,s.bo)((0,s.Lk)("input",{type:"password","onUpdate:modelValue":t[1]||(t[1]=e=>n.password=e),id:"password"},null,512),[[r.Jo,n.password]])]),t[5]||(t[5]=(0,s.Lk)("button",{type:"submit",class:"submit-button"},"Log in",-1))],32),(0,s.Lk)("div",U,[(0,s.bF)(u,{to:"/users/emails/search",class:"accept_bt"},{default:(0,s.k6)((()=>t[6]||(t[6]=[(0,s.eW)("Find Email")]))),_:1}),(0,s.bF)(u,{to:"/users/password",class:"accept_bt"},{default:(0,s.k6)((()=>t[7]||(t[7]=[(0,s.eW)("Change Password")]))),_:1})])])}var D={data(){return{email:"",password:""}},methods:{async login(){try{const e=await this.$axios.post("/users/login",{email:this.email,password:this.password}),t=e.data;let o=t.token||t;const r=t.userId;o&&o.startsWith("Bearer ")&&(o=o.replace("Bearer ","")),o&&(localStorage.setItem("token",o),localStorage.setItem("user_id",r),this.$router.push("/"))}catch(e){console.error("로그인 실패:",e),alert("로그인 실패! 이메일과 비밀번호를 확인해주세요.")}}}};const V=(0,k.A)(D,[["render",X]]);var q=V;function M(e,t,o,r,a,n){return(0,s.uX)(),(0,s.CE)("div",null,t[0]||(t[0]=[(0,s.Lk)("h1",null,"마이페이지",-1)]))}var T={name:"MyPage",data(){return{userId:this.$route.params.user_id,today:this.$route.params.today}},created(){this.$axios.get("`/myPage/${this.userId}/${this.today}`").then((e=>{console.log(e)})).catch((e=>{console.error("페이지 로드 중 오류:",e)}))}};const B=(0,k.A)(T,[["render",M],["__scopeId","data-v-757de60e"]]);var H=B;function R(e,t){return(0,s.uX)(),(0,s.CE)("div",null," 커뮤니티 ")}const Q={},z=(0,k.A)(Q,[["render",R]]);var Y=z;const G={class:"container"},K={class:"input-group"};function Z(e,t,o,a,n,i){return(0,s.uX)(),(0,s.CE)("div",G,[t[4]||(t[4]=(0,s.Lk)("h2",{class:"find-email-title"},"Find Email",-1)),(0,s.Lk)("form",{onSubmit:t[1]||(t[1]=(0,r.D$)(((...e)=>i.submitForm&&i.submitForm(...e)),["prevent"])),class:"find-email-form"},[(0,s.Lk)("div",K,[t[2]||(t[2]=(0,s.Lk)("label",{for:"phoneNumber"},"Phone Number:",-1)),(0,s.bo)((0,s.Lk)("input",{type:"text",id:"phoneNumber","onUpdate:modelValue":t[0]||(t[0]=e=>n.phoneNumber=e),required:""},null,512),[[r.Jo,n.phoneNumber]])]),t[3]||(t[3]=(0,s.Lk)("button",{type:"submit",class:"submit-button"},"Find",-1))],32)])}var ee={data(){return{phoneNumber:""}},methods:{validatePhoneNumber(e){const t=/^[0-9]+$/;return t.test(e)},submitForm(){this.validatePhoneNumber(this.phoneNumber)?this.$axios.post("/users/emails/search",{phoneNumber:this.phoneNumber}).then((e=>{Array.isArray(e.data)&&(alert("찾은 이메일: "+e.data.join(", ")),this.$router.push("/users/login"))})).catch((e=>{e.response&&404===e.response.status?alert(e.response.data):alert("이메일 찾기 중 오류가 발생했습니다."),console.error("이메일 찾기 실패:",e)})):alert("전화번호는 숫자만 입력 가능합니다.")}}};const te=(0,k.A)(ee,[["render",Z]]);var oe=te;const re={class:"container"},se={class:"input-group"},ae={class:"input-group"};function ne(e,t,o,a,n,i){return(0,s.uX)(),(0,s.CE)("div",re,[t[6]||(t[6]=(0,s.Lk)("h2",{class:"change-password-title"},"Change the Password",-1)),(0,s.Lk)("form",{onSubmit:t[2]||(t[2]=(0,r.D$)(((...e)=>i.submitForm&&i.submitForm(...e)),["prevent"])),class:"change-password-form"},[(0,s.Lk)("div",se,[t[3]||(t[3]=(0,s.Lk)("label",{for:"email"},"Email:",-1)),(0,s.bo)((0,s.Lk)("input",{type:"text",id:"email","onUpdate:modelValue":t[0]||(t[0]=e=>n.email=e),required:""},null,512),[[r.Jo,n.email]])]),(0,s.Lk)("div",ae,[t[4]||(t[4]=(0,s.Lk)("label",{for:"password"},"Password:",-1)),(0,s.bo)((0,s.Lk)("input",{type:"password",id:"password","onUpdate:modelValue":t[1]||(t[1]=e=>n.password=e),required:""},null,512),[[r.Jo,n.password]])]),t[5]||(t[5]=(0,s.Lk)("button",{type:"submit",class:"submit-button"},"Apply",-1))],32)])}var ie={data(){return{email:"",password:""}},methods:{validatePassword(e){return e.length>=8},submitForm(){this.validatePassword(this.password)?this.$axios.post("/users/password",{email:this.email,password:this.password}).then((e=>{alert("비밀번호가 변경되었습니다."),console.log(e.data),this.$router.push("/users/login")})).catch((e=>{e.response&&404==e.response.status?alert(e.response.data):alert("비밀번호 변경 중 오류가 발생했습니다.")})):alert("비밀번호는 8자리 이상이어야 합니다.")}}};const ue=(0,k.A)(ie,[["render",ne]]);var le=ue;const de=[{path:"/",name:"HomeForm",component:P},{path:"/users/save",name:"SaveForm",component:A},{path:"/users/login",name:"LoginForm",component:q},{path:"/myPage/:user_id/:date",name:"MyPageForm",component:H},{path:"/board",name:"BoardForm",component:Y},{path:"/users/emails/search",name:"FindEmailByPhoneNumberForm",component:oe},{path:"/users/password",name:"ChangePasswordForm",component:le}],ce=(0,L.aE)({history:(0,L.LA)(),routes:de});var me=ce,pe=o(4373);const he=(0,r.Ef)(v);he.use(me),he.config.globalProperties.$axios=pe.A,he.mount("#app")},9928:function(e,t,o){e.exports=o.p+"img/image1.c474ef99.jpg"},855:function(e,t,o){e.exports=o.p+"img/image2.86df049b.jpg"}},t={};function o(r){var s=t[r];if(void 0!==s)return s.exports;var a=t[r]={exports:{}};return e[r].call(a.exports,a,a.exports,o),a.exports}o.m=e,function(){var e=[];o.O=function(t,r,s,a){if(!r){var n=1/0;for(d=0;d<e.length;d++){r=e[d][0],s=e[d][1],a=e[d][2];for(var i=!0,u=0;u<r.length;u++)(!1&a||n>=a)&&Object.keys(o.O).every((function(e){return o.O[e](r[u])}))?r.splice(u--,1):(i=!1,a<n&&(n=a));if(i){e.splice(d--,1);var l=s();void 0!==l&&(t=l)}}return t}a=a||0;for(var d=e.length;d>0&&e[d-1][2]>a;d--)e[d]=e[d-1];e[d]=[r,s,a]}}(),function(){o.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return o.d(t,{a:t}),t}}(),function(){o.d=function(e,t){for(var r in t)o.o(t,r)&&!o.o(e,r)&&Object.defineProperty(e,r,{enumerable:!0,get:t[r]})}}(),function(){o.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){o.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){o.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){o.p="/"}(),function(){var e={524:0};o.O.j=function(t){return 0===e[t]};var t=function(t,r){var s,a,n=r[0],i=r[1],u=r[2],l=0;if(n.some((function(t){return 0!==e[t]}))){for(s in i)o.o(i,s)&&(o.m[s]=i[s]);if(u)var d=u(o)}for(t&&t(r);l<n.length;l++)a=n[l],o.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return o.O(d)},r=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];r.forEach(t.bind(null,0)),r.push=t.bind(null,r.push.bind(r))}();var r=o.O(void 0,[504],(function(){return o(9636)}));r=o.O(r)})();
//# sourceMappingURL=app.024c12f6.js.map