function ChartFactory() {

    ChartController.prototype.init = WW.commonSuperFunctions.init;

    function ChartController() {

        this.htmlTplPath = "/panels/chart/chart.html";

        this.doInit = function() {

        };

        this.paint = function(chartType, multiDimsRequest) {
            if (multiDimsRequest.sets.length > 2) {
                alert("There are too many axes.");
                return;
            }
            switch (chartType) {
                case "basic_bar":
                    this.paintBasicBar(multiDimsRequest);
                    break;
                case "smooth_line":
                    this.paintSmoothLine(multiDimsRequest);
                    break;
            }
        };

        this.paintSmoothLine = function(mdr) {
            if (mdr.sets.length == 1)
                this.paintSmoothLine_1D(mdr);
            else
                this.paintSmoothLine_2D(mdr);
        };

        this.paintSmoothLine_1D = function(mdr) {
            var option = {
                xAxis: {
                    type: 'category',
                    data: []
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: [],
                    type: 'line',
                    smooth: true
                }]
            };

            var uniqueTuples = mdr.sets[0].ts;

            uniqueTuples.forEach(function(tp) {
                option.xAxis.data.push(tp.display);
            });

            mdr.values.forEach(function(meaVal) {
                option.series[0].data.push(meaVal);
            });

            echarts.init(document.getElementById(this.divId), "theme").setOption(option);
        };

        this.paintSmoothLine_2D = function(mdr) {
            var option = {
//                tooltip: {
//                    trigger: 'axis'
//                },
//                legend: {
//                    data: []
//                },
//                grid: {
//                    left: '3%',
//                    right: '4%',
//                    bottom: '3%',
//                    containLabel: true
//                },
//                toolbox: {
//                    feature: {
//                        saveAsImage: {}
//                    }
//                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: []
                },
                yAxis: {
                    type: 'value'
                },
                series: []
            };

            var rowTuples = mdr.sets[0].ts;
            var colTuples = mdr.sets[1].ts;

            colTuples.forEach(function(tupleInfo) {
                option.xAxis.data.push(tupleInfo.display);
            });

            for (var i = 0; i < rowTuples.length; i++) {
                var tupleInfo = rowTuples[i];
                // option.legend.data.push(tupleInfo.display);
                var series_term = {
                    name: tupleInfo.display,
                    type: "line",
                    stack: "Total",
                    smooth: true,
                    data: []
                };
                for (var c = 0; c < rowTuples.length; c++) {
                    series_term.data.push(mdr.values[i * rowTuples.length + c]);
                }
                option.series.push(series_term);
            }

            echarts.init(document.getElementById(this.divId), "theme").setOption(option);
        };

        this.paintBasicBar = function(mdr) {
            if (mdr.sets.length == 1) {
                this.paintBasicBar_1D(mdr);
                console.log("------------------------- paintBasicBar 1D");
            } else {
                this.paintBasicBar_2D(mdr);
                console.log("------------------------- paintBasicBar 2D");
            }
        };

        this.paintBasicBar_1D = function(mdr) {
            var option = {
                xAxis: {
                    type: 'category',
                    data: []
                },
                yAxis: {
                    type: "value"
                },
                series: [
                    {
                        data: [],
                        type: "bar"
                    }
                ]
            };

            mdr.sets[0].ts.forEach(function(tupleInfo) {
                option.xAxis.data.push(tupleInfo.display);
            });
            mdr.values.forEach(function(measureVal) {
                option.series[0].data.push(measureVal);
            });

            echarts.init(document.getElementById(this.divId), "theme").setOption(option);
        };

        this.paintBasicBar_2D = function(mdr) {

            var option = {
//                legend: {},
                tooltip: {},
                dataset: {
                    source: [['product']]
                },
                xAxis: { type: 'category' },
                yAxis: {},
                series: []
            };

            var rowTuples = mdr.sets[0].ts;
            var colTuples = mdr.sets[1].ts;

            colTuples.forEach(function(tupleInfo) {
                option.dataset.source[0].push(tupleInfo.display);
                option.series.push({ type: 'bar' });
            });
            rowTuples.forEach(function(tupleInfo) {
                option.dataset.source.push([tupleInfo.display]);
            });

            for (var r = 0; r < rowTuples.length; r++) {
                for (var c = 0; c < rowTuples.length; c++) {
                    option.dataset.source[r + 1].push(mdr.values[r * rowTuples.length + c]);
                }
            }

            echarts.init(document.getElementById(this.divId), "theme").setOption(option);
        };

    }

    this.newController = function() {
        return new ChartController();
    };

    $.ajax({
        url: "/panels/chart/theme.json",
        success: function(theme) {
            echarts.registerTheme('theme', theme);
        }
    });

};

WW.registerFactory(new ChartFactory(), "chart/chart");