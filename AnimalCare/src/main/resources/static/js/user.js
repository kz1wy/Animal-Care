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

var barChartOptions = {
    series: [{
      data: [10, 8, 6, 4, 2]
    }],
    chart: {
      type: 'bar',
      height: 350,
      toolbar: {
        show: false
      },
    },
    colors: [
      "#246dec",
      "#cc3c43",
      "#367952",
      "#f5b74f",
      "#4f35a1"
    ],
    plotOptions: {
      bar: {
        distributed: true,
        borderRadius: 4,
        horizontal: false,
        columnWidth: '40%',
      }
    },
    dataLabels: {
      enabled: false
    },
    legend: {
      show: false
    },
    xaxis: {
      categories: ["Fever and cough", "Not eat", "Broken leg", "Skin rash", "Influenza"],
    },
    yaxis: {
      title: {
        text: "Count"
      }
    }
  };

  var barChart = new ApexCharts(document.querySelector("#bar-chart"), barChartOptions);
  barChart.render();


  var areaChartOptions = {
    series: [{
      name: 'ill',
      data: [100, 180, 160, 220, 210, 109, 100]
    }, {
      name: 'cured',
      data: [50, 40, 60, 0, 10, 111, 120]
    }],
    chart: {
      height: 350,
      type: 'area',
      toolbar: {
        show: false,
      },
    },
    colors: ["#4f35a1", "#246dec"],
    dataLabels: {
      enabled: false,
    },
    stroke: {
      curve: 'smooth'
    },
    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"],
    markers: {
      size: 0
    },



    tooltip: {
      shared: true,
      intersect: false,
    }
  };

  var areaChart = new ApexCharts(document.querySelector("#area-chart"), areaChartOptions);
  areaChart.render();