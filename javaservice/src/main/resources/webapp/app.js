var chart;

function requestData() {
  $.ajax({
    url: 'http://rpi207:9000/api/rest/currentweights',
    success: function (data) {
      var series = chart.series[0],
        shift = series.data.length > 20; // shift if the series is
                                         // longer than 20
      // add the point
      var point = data[0].value;
      chart.series[0].addPoint(point, true, shift);

      var unit = $('input').val();
      $('#result').html(point/unit);

      // call it again
      setTimeout(requestData, 500);
    },
    cache: false
  });
}


$(document).ready(function () {

  chart = new Highcharts.Chart({

    chart: {
      type: 'spline',
      renderTo: 'container',
      animation: Highcharts.svg,
      marginRight: 10,
      events: {
        load: requestData
      }
    },
    credits: {
      enabled: false
    },
    title: {
      text: 'Stockwaage'
    },
    xAxis: {
      type: 'datetime',
      tickPixelInterval: 150
    },
    yAxis: {
      title: {
        text: 'Weight'
      },
      plotLines: [{
        value: 0,
        width: 1,
        color: '#808080'
      }]
    },
    tooltip: {
      formatter: function () {
        return '<b>' + this.series.name + '</b><br/>' +
          Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
          Highcharts.numberFormat(this.y, 2);
      }
    },
    legend: {
      enabled: false
    },
    exporting: {
      enabled: false
    },
    series: [{
      name: 'Random data',
      data: []
    }]
  });
});
