(this.webpackJsonpblogapi=this.webpackJsonpblogapi||[]).push([[0],[,,,,,,,,,,,,,function(e,t,s){},,,,,,function(e,t,s){},,,,,function(e,t,s){},function(e,t,s){},function(e,t,s){},,,,,,,,,,,,,,,function(e,t,s){},,,function(e,t,s){},,function(e,t,s){},function(e,t,s){},function(e,t,s){},function(e,t,s){},function(e,t,s){},function(e,t,s){},function(e,t,s){"use strict";s.r(t);var a=s(2),c=s.n(a),n=s(27),r=s.n(n),i=s(10),o=s(5),l=s(54),j=s(1);const d=Object(a.createContext)(),b=()=>Object(a.useContext)(d),h=e=>{let{children:t}=e;const[s,c]=Object(a.useState)(window.sessionStorage.getItem("token"));Object(a.useEffect)((()=>{s?(l.a.defaults.headers.common.Authorization=`Bearer ${s}`,window.sessionStorage.setItem("token",s)):(delete l.a.defaults.headers.common.Authorization,window.sessionStorage.removeItem("token"))}),[s]);return Object(j.jsx)(d.Provider,{value:{token:s,saveToken:e=>{c(e),e?(l.a.defaults.headers.common.Authorization=`Bearer ${e}`,window.sessionStorage.setItem("token",e)):(delete l.a.defaults.headers.common.Authorization,window.sessionStorage.removeItem("token"))}},children:t})};s(41);var u=function(){const[e,t]=c.a.useState(""),[s,a]=c.a.useState(""),n=Object(o.m)(),{saveToken:r}=b();return Object(j.jsxs)("div",{className:"login-container",children:[Object(j.jsx)("div",{className:"login-header",children:"Log in to YYYYYelppppppp"}),Object(j.jsxs)("form",{onSubmit:t=>{t.preventDefault();const a={email:e,password:s};l.a.post("http://localhost:8080/api/v1/auth/authenticate",a).then((e=>{const t=e.data;console.log("Extracted token:",t),t?(localStorage.setItem("username",t.username),localStorage.setItem("role",t.role),console.log(t.role),localStorage.setItem("userId",t.userId),console.log(t.userId),r(t.token),console.log(t.token),n("/list")):(console.log("Authentication failed: No token received."),alert("Login Failed. Please check your credentials."))})).catch((e=>{console.error("Login error:",e),alert("Login Failed. Please check your credentials and try again.")}))},className:"login-form",children:[Object(j.jsx)("input",{type:"text",placeholder:"Email",value:e,onChange:e=>t(e.target.value),className:"login-input"}),Object(j.jsx)("input",{type:"password",placeholder:"Password",value:s,onChange:e=>a(e.target.value),className:"login-input"}),Object(j.jsx)("button",{type:"submit",className:"login-button",children:"Log In"}),Object(j.jsxs)("div",{className:"login-links",children:[Object(j.jsx)("a",{href:"/forgot-password",className:"forgot-password",children:"Forgot password?"}),Object(j.jsx)("a",{href:"/signup",className:"signup-link",children:"New to Yelp? Sign up"})]})]})]})},m=(s(19),s(13),s(11));var O=e=>{let{onSearch:t}=e;const[s,c]=Object(a.useState)(""),[n,r]=Object(a.useState)(""),o=!!localStorage.getItem("token"),l=localStorage.getItem("role"),d=localStorage.getItem("username"),b=()=>{let e="";const a={};s&&n?(e="searchByNameAndLocation",a.name=s,a.location=n):s?(e="search",a.name=s,a.location="Stony Brook"):n&&(e="searchByLocation",a.location=n),fetch("http://localhost:8080/api/v1/restaurants/"+e,{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(a)}).then((e=>e.json())).then((e=>{t(e),console.log(e)})).catch((e=>{console.error("Error performing the search:",e)}))},h=e=>{"Enter"===e.key&&b()},u={backgroundColor:(()=>{const e=["#e57373","#64b5f6","#81c784","#ffb74d","#ba68c8"];return e[Math.floor(Math.random()*e.length)]})()},O=e=>{let{role:t}=e;return Object(j.jsx)("div",{className:"dropdown-menu",children:"owner"===t?Object(j.jsxs)(j.Fragment,{children:[Object(j.jsx)("a",{href:"/manage-restaurant",className:"dropdown-item",children:"Manage your restaurant"}),Object(j.jsx)("a",{href:"/account-settings",className:"dropdown-item",children:"Account settings"})]}):Object(j.jsxs)(j.Fragment,{children:[Object(j.jsx)("a",{href:"/manage-favorites",className:"dropdown-item",children:"Manage your favorites"}),Object(j.jsx)("a",{href:"/account-settings",className:"dropdown-item",children:"Account settings"})]})})};return Object(j.jsxs)("header",{className:"header",children:[Object(j.jsxs)("div",{className:"logo",children:[Object(j.jsx)(m.f,{size:"30",style:{color:"white"}}),"YYYYYelppppppp"]}),Object(j.jsxs)("div",{className:"searchContainer",children:[Object(j.jsx)("input",{type:"text",placeholder:"Restaurants",className:"searchInput",value:s,onChange:e=>c(e.target.value),onKeyDown:h}),Object(j.jsx)("input",{type:"text",placeholder:"Brookhaven, NY 11719",className:"locationInput",value:n,onChange:e=>r(e.target.value),onKeyDown:h}),Object(j.jsx)("button",{className:"searchButton",onClick:b,children:Object(j.jsx)(m.e,{})})]}),Object(j.jsx)("div",{className:"navLinks",children:o?Object(j.jsxs)("div",{className:"user-section",children:["owner"===l&&Object(j.jsx)(i.b,{to:"/start-business",className:"navLink-button",children:"Start Your Business"}),Object(j.jsxs)("div",{className:"userIcon",style:u,children:[d?d.charAt(0).toUpperCase():"U",Object(j.jsx)(O,{role:l})]})]}):Object(j.jsxs)(j.Fragment,{children:[Object(j.jsx)(i.b,{to:"/start-business",className:"navLink",children:"Yelp for Business"}),Object(j.jsx)(i.b,{to:"/start-business",className:"navLink",children:"Write a Review"}),Object(j.jsx)(i.b,{to:"/login",className:"navLink-button",children:"Log In"}),Object(j.jsx)(i.b,{to:"/signup",className:"navLink-button",children:"Sign Up"})]})})]})},x=s(9),p=s(8),g=s(28);const v=()=>Object(j.jsxs)("div",{className:"filterContainer",children:[Object(j.jsx)("h2",{children:"Filters"}),Object(j.jsxs)("div",{className:"filterSection",children:[Object(j.jsx)("h3",{children:"Price"}),Object(j.jsxs)("div",{className:"filterButtonContainer",children:[Object(j.jsx)("button",{className:"filterButton",children:"$"}),Object(j.jsx)("button",{className:"filterButton",children:"$$"}),Object(j.jsx)("button",{className:"filterButton",children:"$$$"}),Object(j.jsx)("button",{className:"filterButton",children:"$$$$"})]})]}),Object(j.jsxs)("div",{className:"filterSection",children:[Object(j.jsx)("h3",{children:"Suggested"}),Object(j.jsxs)("label",{className:"checkboxLabel",children:[Object(j.jsx)("input",{type:"checkbox"})," Open Now"]}),Object(j.jsxs)("label",{className:"checkboxLabel",children:[Object(j.jsx)("input",{type:"checkbox"})," Offers Delivery"]}),Object(j.jsxs)("label",{className:"checkboxLabel",children:[Object(j.jsx)("input",{type:"checkbox"})," Offers Takeout"]}),Object(j.jsxs)("label",{className:"checkboxLabel",children:[Object(j.jsx)("input",{type:"checkbox"})," Good for Dinner"]}),Object(j.jsxs)("label",{className:"checkboxLabel",children:[Object(j.jsx)("input",{type:"checkbox"})," Outdoor Seating"]}),Object(j.jsxs)("label",{className:"checkboxLabel",children:[Object(j.jsx)("input",{type:"checkbox"})," Good for Lunch"]})]}),Object(j.jsxs)("div",{className:"filterSection",children:[Object(j.jsx)("h3",{children:"Category"}),Object(j.jsxs)("div",{className:"categoryButtonContainer",children:[Object(j.jsx)("button",{className:"categoryButton",children:"New American"}),Object(j.jsx)("button",{className:"categoryButton",children:"Italian"}),Object(j.jsx)("button",{className:"categoryButton",children:"Restaurants"}),Object(j.jsx)("button",{className:"categoryButton",children:"American"}),Object(j.jsx)("button",{className:"categoryButton",children:"Steakhouses"}),Object(j.jsx)("button",{className:"categoryButton",children:"Seafood"})]})]}),Object(j.jsx)("div",{className:"seeAll",children:"See all"})]}),N=e=>{let{id:t,imageUrls:s,name:a,rating:c,cuisine:n,price:r,isOpen:i,features:l,description:d,reviews:b}=e;const h=Object(o.m)(),u=s&&s.length>0?s[0].url:"default-image.jpg",m=e=>e>=4?"red":e>=3?"orange":"yellow",O=n?n.split(",").map((e=>e.trim())):[];return Object(j.jsxs)("div",{className:"restaurantEntry",onClick:()=>h(`/detail/${t}`),children:[Object(j.jsx)("img",{src:u,alt:a,className:"restaurantImage"}),Object(j.jsxs)("div",{className:"restaurantDetails",children:[Object(j.jsx)("h2",{className:"restaurantName",children:a}),Object(j.jsxs)("div",{className:"restaurantMeta",children:[Object(j.jsx)("span",{className:"restaurantRating",children:Array.from({length:Math.round(c)},((e,t)=>Object(j.jsx)(x.a,{icon:p.g,color:m(c)},t)))}),Object(j.jsxs)("span",{className:"restaurantReviews",children:["(",b," reviews)"]}),Object(j.jsxs)("div",{className:"restaurantCuisines",children:[O.map((e=>Object(j.jsx)("span",{className:"tag",children:e},e))),Object(j.jsx)("span",{className:"restaurantPrice",children:r})]})]}),Object(j.jsxs)("div",{className:"restaurantDescription",children:[Object(j.jsx)(x.a,{icon:g.a})," ",d]})]})]})};var f=()=>{const[e,t]=Object(a.useState)([]),s=Object(o.m)();Object(a.useEffect)((()=>{const e=localStorage.getItem("role");console.log(e);const s=localStorage.getItem("userId");console.log(s);let a="http://localhost:8080/api/v1/restaurants/searchByLocation",c=JSON.stringify({location:"Stony Brook"});"owner"===e&&(a="http://localhost:8080/api/v1/restaurants/searchByOwner",c=JSON.stringify({createdBy:s})),fetch(a,{method:"POST",headers:{"Content-Type":"application/json"},body:c}).then((e=>e.json())).then((e=>{t(e),console.log(e)})).catch((e=>console.error("Error:",e)))}),[]);return Object(j.jsxs)("div",{className:"wrapper",children:[Object(j.jsx)(O,{onSearch:e=>{t(e),console.log(e)}}),Object(j.jsxs)("div",{className:"content",children:[Object(j.jsx)(v,{}),Object(j.jsx)("div",{className:"restaurantsList",children:e.length>0?e.map(((e,t)=>Object(j.jsx)(N,{...e,onClick:()=>s(`/detail/${e.id}`)},t))):Object(j.jsx)("div",{children:"No restaurants found"})})]})]})};s(44);var w=function(){const[e,t]=Object(a.useState)(""),[s,c]=Object(a.useState)(""),[n,r]=Object(a.useState)(""),[i,d]=Object(a.useState)("customer"),b=Object(o.m)();return Object(j.jsxs)("div",{className:"signup-container",children:[Object(j.jsx)("div",{className:"login-header",children:"Sign in to YYYYYelppppppp"}),Object(j.jsxs)("form",{onSubmit:async t=>{t.preventDefault();const a={username:e,email:s,password:n,role:i};try{const e=await l.a.post("http://localhost:8080/api/v1/auth/signup",a);console.log("Signup successful:",e.data),b("/login")}catch(c){console.error("Signup error:",c.response.data)}},className:"signup-form",children:[Object(j.jsx)("input",{type:"text",placeholder:"Username",value:e,onChange:e=>t(e.target.value),className:"signup-input"}),Object(j.jsx)("input",{type:"email",placeholder:"Email",value:s,onChange:e=>c(e.target.value),className:"signup-input"}),Object(j.jsx)("input",{type:"password",placeholder:"Password",value:n,onChange:e=>r(e.target.value),className:"signup-input"}),Object(j.jsxs)("div",{className:"role-selection",children:[Object(j.jsx)("div",{children:"Sign Up As"}),Object(j.jsxs)("label",{children:[Object(j.jsx)("input",{type:"radio",name:"role",value:"customer",checked:"customer"===i,onChange:()=>d("customer")})," Customer"]}),Object(j.jsxs)("label",{children:[Object(j.jsx)("input",{type:"radio",name:"role",value:"owner",checked:"owner"===i,onChange:()=>d("owner")})," Restaurant Owner"]})]}),Object(j.jsx)("button",{type:"submit",className:"signup-button",children:"Sign Up"}),Object(j.jsxs)("div",{className:"login-link",children:["Already on Yelp? ",Object(j.jsx)("a",{href:"/login",children:"Log in"})]})]})]})},y=s(14),S=s.n(y);s(24),s(25),s(46),s(26),s(47);var k=e=>{let{setRating:t}=e;const[s,c]=Object(a.useState)(0),[n,r]=Object(a.useState)(0);return Object(j.jsxs)("div",{className:"star-rating-input",children:[[...Array(5)].map(((e,a)=>{const i=a+1;return Object(j.jsx)("span",{onClick:()=>{t(i),r(i)},onMouseEnter:()=>c(i),onMouseLeave:()=>c(0),children:Object(j.jsx)(x.a,{icon:p.g,className:"star",size:"2x",color:i<=(s||n)?"#ffc107":"#e4e5e9"})},a)})),Object(j.jsx)("span",{className:"rating-label",style:{color:0===(s||n)?"#9b9b9b":"#000"},children:(e=>{switch(e){case 0:return"Select your rating";case 1:return"Not Good";case 2:return"Could\u2019ve been better";case 3:return"OK";case 4:return"Good";case 5:return"Great";default:return""}})(s||n)})]})};const C=()=>{const[e,t]=Object(a.useState)(0);return Object(j.jsxs)("div",{className:"user-review-prompt",children:[Object(j.jsx)("div",{className:"user-avatar",children:Object(j.jsx)("img",{src:"https://s3-media0.fl.yelpcdn.com/assets/public/default_user_avatar_64x64_v2.yji-19e0a8ff85b15f4bbd79.png",alt:"User Avatar",className:"avatar-img"})}),Object(j.jsxs)("div",{className:"user-details",children:[Object(j.jsx)("div",{style:{fontWeight:"bold"},children:"Username"}),Object(j.jsx)("div",{children:"Location"}),Object(j.jsxs)("div",{className:"user-metrics",children:[Object(j.jsx)(x.a,{icon:p.b})," ",Object(j.jsx)("span",{children:"0"}),Object(j.jsx)(x.a,{icon:p.g})," ",Object(j.jsx)("span",{children:"0"})]})]}),Object(j.jsxs)("div",{className:"user-review-action",children:[Object(j.jsx)(k,{setRating:t}),Object(j.jsx)("a",{href:"#",className:"start-review-link",children:"Start your review of Popeyes Louisiana Kitchen"})]})]})},I=e=>{let{averageRating:t,reviewCount:s,reviews:a}=e;const c=a.reduce(((e,t)=>{const s=Math.round(t.score);return e[s]=e[s]?e[s]+1:1,e}),{}),n=Object.values(c).reduce(((e,t)=>e+t),0);return console.log(t),Object(j.jsxs)("div",{className:"overall-rating-container",children:[Object(j.jsxs)("div",{className:"overall-rating",children:[Object(j.jsx)("span",{className:"rating-title",children:"Overall rating"}),Object(j.jsx)("div",{className:"rating-stars",children:[...Array(5)].map(((e,s)=>{const a=s<Math.round(t);return console.log(`Star ${s+1}: ${a?"active":"inactive"}`),Object(j.jsx)(x.a,{icon:p.g,className:"star-icon",color:a?"#ffc107":"#e4e5e9"},s)}))}),Object(j.jsxs)("span",{className:"review-count",children:[s," reviews"]})]}),Object(j.jsx)("div",{className:"rating-breakdown",children:[5,4,3,2,1].map((e=>Object(j.jsxs)("div",{className:"rating-row",children:[Object(j.jsxs)("span",{children:[e," ",1===e?"star":"stars"]}),Object(j.jsx)("div",{className:"rating-bar",children:Object(j.jsx)("div",{className:`rating-fill rating-${e}`,style:{width:`${c[e]?(c[e]/n*100).toFixed(1):0}%`}})})]},e)))})]})},L=e=>{let{onSearch:t}=e;return Object(j.jsxs)("div",{className:"review-filters",children:[Object(j.jsxs)("div",{className:"filter-buttons",children:[Object(j.jsxs)("button",{className:"filter-button",children:["Yelp Sort",Object(j.jsx)(x.a,{icon:p.c,className:"caret-icon"})]}),Object(j.jsxs)("button",{className:"filter-button",children:["Filter by rating",Object(j.jsx)(x.a,{icon:p.c,className:"caret-icon"})]})]}),Object(j.jsxs)("div",{className:"search-reviews",children:[Object(j.jsx)("input",{type:"text",placeholder:"Search reviews",onChange:e=>t(e.target.value)}),Object(j.jsx)(x.a,{icon:p.f})]})]})},A=e=>{let{reviews:t}=e;const[s,c]=Object(a.useState)(!1),n=Object(a.useRef)(null),r=e=>{n.current&&!n.current.contains(e.target)&&c(!1)};return Object(a.useEffect)((()=>(document.addEventListener("mousedown",r),()=>{document.removeEventListener("mousedown",r)})),[]),Object(j.jsx)("div",{className:"review-list",children:t.map(((e,t)=>Object(j.jsxs)("div",{className:"review-item",children:[Object(j.jsx)("div",{className:"review-avatar",children:Object(j.jsx)("img",{src:"https://s3-media0.fl.yelpcdn.com/assets/public/default_user_avatar_64x64_v2.yji-19e0a8ff85b15f4bbd79.png",alt:"Review Avatar",className:"review-avatar-img"})}),Object(j.jsxs)("div",{className:"review-content",children:[Object(j.jsx)("div",{className:"review-header",children:Object(j.jsx)("h5",{children:e.username||"Anonymous"})}),Object(j.jsxs)("div",{className:"review-metrics",children:[Object(j.jsxs)("span",{children:["162 ",Object(j.jsx)(x.a,{icon:p.b})]}),Object(j.jsxs)("span",{children:["500 ",Object(j.jsx)(x.a,{icon:p.g})]}),Object(j.jsxs)("span",{children:["462 ",Object(j.jsx)(x.a,{icon:p.g})]})]}),Object(j.jsx)("div",{className:"review-rating",children:[...Array(e.score)].map(((e,t)=>Object(j.jsx)(x.a,{icon:p.g,className:"star-icon"},t)))}),Object(j.jsx)("p",{className:"review-text",children:e.body})]}),Object(j.jsxs)("div",{className:"review-actions",ref:n,children:[Object(j.jsx)(x.a,{icon:p.d,className:"ellipsis-icon",onClick:()=>c(!s)}),s&&Object(j.jsxs)("div",{className:"action-menu",children:[Object(j.jsx)("div",{className:"action-item",children:"Share review"}),Object(j.jsx)("div",{className:"action-item",children:"Embed review"})]})]})]},t)))})},B=e=>{let{restaurantId:t,onCancel:s}=e;const[c,n]=Object(a.useState)(0),[r,i]=Object(a.useState)(""),[o,d]=Object(a.useState)({food:!1,service:!1,ambiance:!1}),b=e=>{d({...o,[e]:!o[e]})};return Object(j.jsxs)("div",{className:"write-review-page",children:[Object(j.jsx)("h1",{children:"Write a Review"}),Object(j.jsxs)("div",{className:"review-input",children:[Object(j.jsx)(k,{setRating:n}),Object(j.jsx)("textarea",{placeholder:"Write your review here...",value:r,onChange:e=>i(e.target.value)}),Object(j.jsxs)("div",{className:"tags",children:[Object(j.jsx)("span",{onClick:()=>b("food"),className:o.food?"tag active":"tag",children:"Food"}),Object(j.jsx)("span",{onClick:()=>b("service"),className:o.service?"tag active":"tag",children:"Service"}),Object(j.jsx)("span",{onClick:()=>b("ambiance"),className:o.ambiance?"tag active":"tag",children:"Ambiance"})]})]}),Object(j.jsx)("button",{className:"post-review-button",onClick:()=>{const e={headers:{Authorization:`Bearer ${window.sessionStorage.getItem("token")}`}},a={restaurantId:t,rating:c,reviewText:r,tags:o};l.a.post("http://localhost:8080/api/reviews",a,e).then((e=>{console.log(e.data),s()})).catch((e=>{console.error(e)}))},children:"Post Review"}),Object(j.jsx)("button",{className:"cancel-button",onClick:s,children:"Cancel"})]})};var E=e=>{let{reviews:t,averageRating:s,restaurantName:c}=e;const[n,r]=Object(a.useState)([]),[i,o]=Object(a.useState)(!1);return Object(j.jsx)("div",{className:"recommended-reviews-container",children:i?Object(j.jsx)(B,{restaurantName:c,onCancel:()=>o(!1)}):Object(j.jsxs)(j.Fragment,{children:[Object(j.jsx)("h3",{children:"Recommended Reviews"}),Object(j.jsx)(C,{onWriteReview:()=>o(!0)}),Object(j.jsx)(I,{averageRating:s,reviewCount:t.length,reviews:t,reviewCountsByStar:{5:10,4:7,3:5,2:2,1:1}}),Object(j.jsx)(L,{onSearch:e=>{const s=t.filter((t=>t.body.toLowerCase().includes(e.toLowerCase())));r(s)}}),Object(j.jsx)(A,{reviews:t})]})})},R=s(29),$=s(21);const F=e=>{let{name:t,restaurantId:s,averageRating:c,reviewCount:n,imageUrls:r}=e;const[i,o]=Object(a.useState)(0);return Object(j.jsxs)("div",{className:"gallery-container",children:[Object(j.jsx)("button",{className:"nav-button left",onClick:()=>{o((e=>Math.max(e-3,0)))},disabled:0===i,children:Object(j.jsx)(m.a,{})}),Object(j.jsx)("div",{className:"images-row",children:r.slice(i,i+3).map(((e,s)=>Object(j.jsx)("span",{className:"image-container",children:Object(j.jsx)("img",{src:e.url,alt:`${t} image ${s+1+i}`})},s)))}),Object(j.jsx)("button",{className:"nav-button right",onClick:()=>{o((e=>Math.min(e+3,r.length-3)))},disabled:i+3>=r.length,children:Object(j.jsx)(m.d,{})}),Object(j.jsxs)("div",{className:"overlay-text",children:[t,Object(j.jsx)("br",{}),"\u2b50 ",c," (",n," reviews)"]}),Object(j.jsxs)("button",{className:"see-all-button",children:[Object(j.jsx)(m.c,{})," See all ",r.length," photos"]})]})},Y=e=>{let{restaurantId:t}=e;const[s,c]=Object(a.useState)(!1);return Object(j.jsxs)("div",{className:"interactive-buttons",children:[Object(j.jsxs)(i.b,{to:`/detail/${t}/write-review`,className:"write-review-link",children:[Object(j.jsx)(x.a,{icon:R.a}),Object(j.jsx)("span",{style:{marginLeft:"8px"},children:"Write a review"})]}),Object(j.jsxs)(i.b,{to:`/detail/${t}/upload-photo`,className:"button-add-photo",children:[Object(j.jsx)(x.a,{icon:p.b}),Object(j.jsx)("span",{style:{marginLeft:"8px"},children:"Add photo"})]}),Object(j.jsxs)("button",{className:"button-share",children:[Object(j.jsx)($.a,{}),Object(j.jsx)("span",{style:{marginLeft:"8px"},children:"Share"})]}),Object(j.jsxs)("button",{className:"button-save",onClick:()=>{if(s)return;const e=localStorage.getItem("userId"),a=localStorage.getItem("token");fetch("http://localhost:8080/api/v1/favorites/add",{method:"POST",headers:{"Content-Type":"application/json",Authorization:`Bearer ${a}`},body:JSON.stringify({userId:e,restaurantId:t})}).then((e=>e.json())).then((e=>{if(!e.success)throw new Error(e.message||"Failed to save");c(!0),alert("Added to favorites!")})).catch((e=>{console.error("Error adding to favorites:",e),alert(e.message)}))},disabled:s,children:[Object(j.jsx)(x.a,{icon:p.a}),Object(j.jsx)("span",{style:{marginLeft:"8px"},children:s?"Saved":"Save"})]})]})},P=e=>{let{email:t,phone:s,location:a,website:c}=e;return Object(j.jsxs)("div",{className:"contact-info",children:[Object(j.jsx)("div",{className:"website-link",children:c}),Object(j.jsxs)("div",{className:"email",children:[Object(j.jsx)("p",{children:"Email:"})," ",t]}),Object(j.jsxs)("div",{className:"phone-number",children:[Object(j.jsx)("p",{children:"Phone:"})," ",s]}),Object(j.jsxs)("div",{className:"get-directions",children:[Object(j.jsx)("strong",{children:"Location:"})," ",a,Object(j.jsx)("a",{href:"#",className:"get-directions-link",children:"Get Directions"})]}),Object(j.jsxs)("button",{className:"edit-button",children:[Object(j.jsx)(x.a,{icon:p.e,style:{marginRight:"8px"}}),"Suggest an edit"]})]})},T=e=>{let{menu:t}=e;const s=e=>{window.location.href=e};return Object(j.jsxs)("div",{className:"menu-container",children:[Object(j.jsx)("h3",{children:"Menu"}),Object(j.jsxs)("div",{children:[Object(j.jsxs)("button",{className:"menu-button",onClick:()=>s(t),children:[Object(j.jsx)($.b,{}),Object(j.jsx)("span",{style:{marginLeft:"8px"},children:"Website menu"})]}),Object(j.jsxs)("button",{className:"menu-button",onClick:()=>s(t),children:[Object(j.jsx)(m.b,{}),Object(j.jsx)("span",{style:{marginLeft:"8px"},children:"Full menu"})]})]})]})},U=e=>{let t,{address:s,hoursJson:a}=e;try{t=JSON.parse(a)}catch(i){console.error("Error parsing hours JSON:",i),t={}}const c=["Mon","Tue","Wed","Thu","Fri","Sat","Sun"],n=S()().format("ddd"),r=Object.entries(t).sort(((e,t)=>c.indexOf(e[0])-c.indexOf(t[0])));return Object(j.jsxs)("div",{className:"location-hours-container",children:[Object(j.jsx)("h3",{children:"Location & Hours"}),Object(j.jsxs)("div",{className:"location-hours-content",children:[Object(j.jsxs)("div",{className:"map-and-details",children:[Object(j.jsx)("img",{src:"https://maps.googleapis.com/maps/api/staticmap?size=315x150&sensor=false&client=gme-yelp&language=en&scale=1&zoom=15&center=40.802938%2C-72.868491&markers=scale%3A1%7Cicon%3Ahttps%3A%2F%2Fyelp-images.s3.amazonaws.com%2Fassets%2Fmap-markers%2Fannotation_32x43.png%7C40.802938%2C-72.868491&signature=XNQ01HCT3t3HQ6cARzDhXl2x7mM=",alt:"Map"}),Object(j.jsxs)("div",{className:"address-direction",children:[Object(j.jsx)("div",{className:"address",children:s}),Object(j.jsx)("button",{className:"get-directions-button",children:"Get directions"})]})]}),Object(j.jsx)("div",{className:"hours",children:Object(j.jsx)("table",{className:"hours-table",children:Object(j.jsx)("tbody",{children:r.map(((e,t)=>{let[s,a]=e;const c=(e=>{if(!e||"closed"===e.toLowerCase())return!1;const[t,s]=e.split(" - "),a=S()(),c=S()(t,"hh:mm A");let n=S()(s,"hh:mm A");return n.isBefore(c)&&n.add(1,"day"),a.isBefore(c)&&"AM"===a.format("A")&&(c.subtract(1,"day"),n.subtract(1,"day")),a.isBetween(c,n)})(a),r=s===n;return Object(j.jsxs)("tr",{className:r?"today":"",children:[Object(j.jsx)("td",{children:s}),Object(j.jsx)("td",{children:Object(j.jsxs)("span",{className:"hours-and-status",children:[a,r&&Object(j.jsx)("span",{className:"status "+(c?"open":"closed"),children:c?"Open now":"Closed now"})]})})]},t)}))})})})]})]})};var D=()=>{const{restaurantId:e}=Object(o.o)(),t=b().token,[s,c]=Object(a.useState)(null),[n,r]=Object(a.useState)([]),[i,l]=Object(a.useState)(0);Object(a.useEffect)((()=>{fetch(`http://localhost:8080/api/v1/restaurants/${e}`).then((e=>e.json())).then((e=>{c(e),d()})).catch((e=>{console.error("Error fetching details:",e)}))}),[e]);const d=()=>{t?fetch(`http://localhost:8080/api/v1/reviews/${e}`,{method:"GET",headers:{"Content-Type":"application/json",Authorization:`Bearer ${t}`}}).then((e=>e.json())).then((e=>{r(e);const t=e.reduce(((e,t)=>e+t.score),0);l((t/e.length).toFixed(1)),console.log((t/e.length).toFixed(1))})).catch((e=>{console.error("Error loading reviews:",e)})):console.log("No token provided. User is not authenticated.")};return s?Object(j.jsxs)("div",{className:"wrapper",children:[Object(j.jsx)(O,{}),Object(j.jsx)(F,{name:s.name,restaurantId:s.id,averageRating:i,reviewCount:n.length,imageUrls:s.imageUrls}),Object(j.jsxs)("div",{className:"main-content",children:[Object(j.jsxs)("div",{className:"info-column",children:[Object(j.jsx)(Y,{restaurantId:s.id}),Object(j.jsx)(T,{website:s.website}),Object(j.jsx)(U,{address:s.location,hoursJson:s.hours}),Object(j.jsx)(E,{restaurantId:s.id,restaurantName:s.name,averageRating:s.rating,reviews:n})]}),Object(j.jsx)("div",{className:"order-column",children:Object(j.jsx)(P,{email:s.email,phone:s.phone,location:s.location,website:s.website})})]})]}):Object(j.jsx)("div",{children:"Loading..."})};var z=function(){const e=b(),t=e.token;console.log("Auth Context:",e),console.log("Token:",e.token);const{restaurantId:s}=Object(o.o)(),[c,n]=Object(a.useState)(""),[r,i]=Object(a.useState)(0),[l,d]=Object(a.useState)(""),h=Object(o.m)();return Object(a.useEffect)((()=>{fetch(`http://localhost:8080/api/v1/restaurants/${s}`).then((e=>e.json())).then((e=>{d(e.name)})).catch((e=>console.error("Failed to load restaurant details",e)))}),[s]),Object(j.jsxs)("div",{children:[Object(j.jsx)("header",{className:"header",children:Object(j.jsx)("div",{className:"logo",children:Object(j.jsx)(m.f,{size:"30",style:{color:"white"}})})}),Object(j.jsxs)("div",{className:"write-review-page",children:[Object(j.jsx)("h2",{children:l}),Object(j.jsxs)("form",{onSubmit:a=>{if(a.preventDefault(),""===c.trim()||0===r)return void alert("Please enter a review and select a score before submitting.");if(!t)return void alert("You are not logged in.");console.log("Attempting to submit review with token:",t);const n={restaurantId:Number(s),userId:e.userId,body:c,score:r};fetch("http://localhost:8080/api/v1/reviews/add",{method:"POST",headers:{"Content-Type":"application/json",Authorization:`Bearer ${t}`},body:JSON.stringify(n)}).then((e=>{if(!e.ok)throw new Error("Failed to submit review");return e.json()})).then((e=>{console.log("Review submitted successfully:",e),h("/detail/write-review/success")})).catch((e=>{console.error("Error submitting review:",e),alert("Error submitting review. Please try again.")}))},className:"review-form",children:[Object(j.jsxs)("div",{className:"review-box",children:[Object(j.jsx)(k,{setRating:i}),Object(j.jsx)("textarea",{value:c,onChange:e=>{n(e.target.value)},placeholder:"Share your experience...",className:"review-textarea"})]}),Object(j.jsx)("button",{type:"submit",className:"post-review-button",children:"Post Review"})]})]})]})},M=(s(48),s(30));var J=function(){return Object(j.jsxs)("div",{children:[Object(j.jsx)(O,{}),Object(j.jsxs)("div",{className:"submit-success-page",children:[Object(j.jsx)(M.a,{size:"50"}),Object(j.jsx)("h2",{children:"Your review is now live!"})]})]})};s(49);var _=function(){const[e,t]=Object(a.useState)({name:"",email:"",location:"",phone:"",cuisine:"",zipcode:"",hours:"",imageUrls:"",menu:"",website:""}),s=Object(o.m)(),c=s=>{const{name:a,value:c}=s.target;t({...e,[a]:c})};return Object(j.jsxs)("div",{className:"dashboard-wrapper",children:[Object(j.jsx)("header",{className:"header",children:Object(j.jsxs)("div",{className:"logo",children:[Object(j.jsx)(m.f,{size:"30",style:{color:"white"}}),"YYYYYelppppppp for business"]})}),Object(j.jsxs)("div",{className:"owner-dashboard",children:[Object(j.jsx)("h2",{className:"dashboard-heading",children:"Great! Now Create Your Business Here"}),Object(j.jsxs)("form",{onSubmit:async t=>{t.preventDefault();try{if(!(await fetch("http://localhost:8080/api/v1/restaurants/add",{method:"POST",headers:{"Content-Type":"application/json",Authorization:`Bearer ${localStorage.getItem("token")}`},body:JSON.stringify(e)})).ok)throw new Error("Failed to add restaurant");alert("Restaurant added successfully!"),s("/list")}catch(a){console.error("Error:",a),alert("Error adding restaurant")}},className:"business-form",children:[Object(j.jsx)("input",{type:"text",name:"name",value:e.name,onChange:c,placeholder:"Business Name"}),Object(j.jsx)("input",{type:"email",name:"email",value:e.email,onChange:c,placeholder:"Email Address"}),Object(j.jsxs)("div",{className:"inline-fields",children:[Object(j.jsx)("div",{className:"field",children:Object(j.jsx)("input",{type:"text",name:"location",value:e.location,onChange:c,placeholder:"Business Location"})}),Object(j.jsx)("div",{className:"field",children:Object(j.jsx)("input",{type:"text",name:"zipcode",value:e.zipcode,onChange:c,placeholder:"ZIP Code"})})]}),Object(j.jsx)("input",{type:"text",name:"phone",value:e.phone,onChange:c,placeholder:"Contact Phone"}),Object(j.jsx)("input",{type:"text",name:"cuisine",value:e.cuisine,onChange:c,placeholder:"Cuisine Type"}),Object(j.jsx)("input",{name:"hours",value:e.hours,onChange:c,placeholder:"Operating Hours"}),Object(j.jsx)("input",{name:"imageUrls",value:e.imageUrls,onChange:c,placeholder:"Image URLs"}),Object(j.jsx)("input",{type:"text",name:"menu",value:e.menu,onChange:c,placeholder:"Menu URL"}),Object(j.jsx)("input",{type:"text",name:"website",value:e.website,onChange:c,placeholder:"Website URL"}),Object(j.jsx)("button",{type:"submit",className:"submit-button",children:"Submit"})]})]})]})};s(50);var G=()=>{const e=b().token,[t,s]=Object(a.useState)([]),[c,n]=Object(a.useState)(!1),{restaurantId:r}=Object(o.o)(),[l,d]=Object(a.useState)(""),h=Object(a.useRef)(null);Object(a.useEffect)((()=>{fetch(`http://localhost:8080/api/v1/restaurants/${r}`).then((e=>e.json())).then((e=>d(e.name))).catch((e=>console.error("Failed to load restaurant details",e)))}),[r]);const u=e=>{e.preventDefault();let t=e.target.files?Array.from(e.target.files):Array.from(e.dataTransfer.files);s((e=>[...e,...t]))};return Object(j.jsxs)("div",{className:"wrapper",children:[Object(j.jsx)("header",{className:"header",children:Object(j.jsx)("div",{className:"logo",children:Object(j.jsx)(m.f,{size:"30",style:{color:"white"}})})}),Object(j.jsxs)("div",{className:"upload-container",children:[Object(j.jsxs)("h2",{children:[Object(j.jsx)(i.b,{to:`/detail/${r}`,className:"restaurant-link",children:l})," : Add Photos"]}),0===t.length?Object(j.jsx)("div",{children:Object(j.jsxs)("div",{className:"drop-area "+(c?"drag-over":""),onDragOver:e=>{e.preventDefault(),n(!0)},onDragLeave:e=>{e.preventDefault(),n(!1)},onDrop:u,children:[Object(j.jsx)("img",{className:"illustration-img",src:"https://s3-media0.fl.yelpcdn.com/assets/public/photo_review_325x200_v2.yji-9de7a3277cea44fd0377.svg",alt:"Drag and drop photos here"}),Object(j.jsx)("h3",{className:"info-text",children:"Drag and drop photos here or"}),Object(j.jsx)("p",{className:"info-text",children:"or"}),Object(j.jsx)("button",{onClick:()=>h.current.click(),className:"browse-button",children:"Browse Files"}),Object(j.jsx)("input",{type:"file",multiple:!0,onChange:u,className:"file-input",ref:h,style:{display:"none"}})]})}):Object(j.jsxs)("div",{children:[Object(j.jsx)("h3",{children:"Almost Done! Add More and Upload!"}),Object(j.jsx)("div",{className:"photos-preview",children:t.map(((e,a)=>Object(j.jsxs)("div",{className:"preview-img-wrapper",children:[Object(j.jsx)("img",{src:URL.createObjectURL(e),alt:`Preview ${a}`,className:"preview-img"}),Object(j.jsx)("button",{className:"delete-img-btn",onClick:()=>(e=>{const a=t.filter(((t,s)=>s!==e));s(a)})(a),children:Object(j.jsx)(x.a,{icon:p.h})})]},a)))}),Object(j.jsxs)("div",{className:"photos-actions",children:[Object(j.jsx)(i.b,{onClick:()=>h.current.click(),className:"browse-link",children:"Browse"}),Object(j.jsx)("span",{className:"info-text",children:"or drag and drop more photos"}),Object(j.jsx)("input",{type:"file",multiple:!0,onChange:u,className:"file-input",ref:h,style:{display:"none"}}),Object(j.jsx)("button",{onClick:()=>{const a=new FormData;t.forEach((e=>a.append("file",e))),fetch(`http://localhost:8080/api/v1/photos/upload/${r}`,{method:"POST",headers:{Authorization:`Bearer ${e}`},body:a}).then((e=>{if(!e.ok)throw new Error("Network response was not ok");return e.json()})).then((()=>{alert("Files uploaded successfully"),s([])})).catch((e=>{console.error("Error uploading files:",e),alert("Error uploading files: "+e.message)}))},className:"upload-button",children:"Upload"})]})]})]})]})};function W(){let e=Object(o.q)([{path:"/",element:Object(j.jsx)(K,{})},{path:"/home",element:Object(j.jsx)(O,{})},{path:"/detail/:restaurantId",element:Object(j.jsx)(D,{})},{path:"/detail/:restaurantId/write-review",element:Object(j.jsx)(z,{})},{path:"/detail/:restaurantId/upload-photo",element:Object(j.jsx)(G,{})},{path:"/detail/write-review/success",element:Object(j.jsx)(J,{})},{path:"/list",element:Object(j.jsx)(f,{})},{path:"/start-business",element:Object(j.jsx)(_,{})},{path:"/login",element:Object(j.jsx)(u,{})},{path:"signup",element:Object(j.jsx)(w,{})}]);return Object(j.jsx)(h,{children:e})}const H=()=>Object(j.jsxs)("form",{style:{margin:"10px",borderColor:"black"},children:[Object(j.jsx)("input",{}),Object(j.jsx)("button",{children:"submit"})]}),K=()=>Object(j.jsxs)("div",{children:[Object(j.jsx)(H,{}),Object(j.jsx)(H,{}),Object(j.jsxs)("article",{children:[Object(j.jsx)("h1",{children:"Hi"}),Object(j.jsxs)("ol",{children:[Object(j.jsx)("li",{children:"Components: UI Building Blocks"}),Object(j.jsx)("li",{children:"Defining a Component"}),Object(j.jsx)("li",{children:"Using a Component"})]})]})]});s(51);var Q=e=>{e&&e instanceof Function&&s.e(3).then(s.bind(null,55)).then((t=>{let{getCLS:s,getFID:a,getFCP:c,getLCP:n,getTTFB:r}=t;s(e),a(e),c(e),n(e),r(e)}))};r.a.createRoot(document.getElementById("root")).render(Object(j.jsx)(i.a,{children:Object(j.jsx)(W,{})})),Q()}],[[52,1,2]]]);
//# sourceMappingURL=main.2c16e432.chunk.js.map