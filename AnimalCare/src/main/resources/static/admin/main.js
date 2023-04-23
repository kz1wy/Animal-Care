// gọi id
function getELE(id){
  return document.getElementById(id);
}
// sidebar
$('.sub-btn').click(function(){
  $(this).next('.sub-menu').slideToggle();
  $(this).find('.arrow').toggleClass('rotate');
});

let sidebar = document.querySelector(".sidebar");
let sbBtn = document.querySelector(".fa-bars");
sbBtn.addEventListener("click", function(){
  sidebar.classList.toggle("active");
});