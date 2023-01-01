function ChartFactory() {

    ChartController.prototype.init = WW.commonSuperFunctions.init;

    function ChartController() {

        this.htmlTplPath = "/panels/chart/chart.html";

        this.doInit = function() {
            // alert(this.divId);

            this.test();

        };

        this.test = function() {
            var option = {
                    title: {
                      text: 'ECharts 入门示例'
                    },
                    tooltip: {},
                    legend: {
                      data: ['销量']
                    },
                    xAxis: {
                      data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
                    },
                    yAxis: {},
                    series: [
                      {
                        name: '销量',
                        type: 'bar',
                        data: [5, 20, 36, 10, 10, 20]
                      }
                    ]
                  };
            echarts.init(document.getElementById(this.divId)).setOption(option);
        };
    }

    this.newController = function() {
        return new ChartController();
    };
};

WW.registerFactory(new ChartFactory(), "chart/chart");