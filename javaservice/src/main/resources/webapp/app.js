var chart;

function requestLiveData() {
  $.ajax({
    url: 'http://rpi207:9000/api/rest/currentweights',
    success: function (data) {
      var series = chart.series[0],
        shift = series.data.length > 20; // shift if the series is
                                         // longer than 20
      // add the point
      var timestamp = Date.parse(data[0].timestamp);
      var value =  data[0].value;
      var coord = [timestamp,value];
      chart.series[0].addPoint(coord, true, shift);

      var unit = $('input').val();
      $('#result').html(value/unit);

      // call it again
      setTimeout(requestLiveData, 1000);
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
        load: requestLiveData
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
