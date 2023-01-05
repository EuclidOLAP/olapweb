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
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
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

    var theme = {
        "color": [
            "#041439",
            "#0a1e4f",
            "#122a63",
            "#1c3778",
            "#29468d",
            "#3958a5",
            "#4b6bbc",
            "#6080d2"
        ],
        "backgroundColor": "rgba(0, 0, 0, 0)",
        "textStyle": {},
        "title": {
            "textStyle": {
                "color": "#464646"
            },
            "subtextStyle": {
                "color": "#6E7079"
            }
        },
        "line": {
            "itemStyle": {
                "borderWidth": 1
            },
            "lineStyle": {
                "width": 2
            },
            "symbolSize": 4,
            "symbol": "emptyCircle",
            "smooth": false
        },
        "radar": {
            "itemStyle": {
                "borderWidth": 1
            },
            "lineStyle": {
                "width": 2
            },
            "symbolSize": 4,
            "symbol": "emptyCircle",
            "smooth": false
        },
        "bar": {
            "itemStyle": {
                "barBorderWidth": 0,
                "barBorderColor": "#ccc"
            }
        },
        "pie": {
            "itemStyle": {
                "borderWidth": 0,
                "borderColor": "#ccc"
            }
        },
        "scatter": {
            "itemStyle": {
                "borderWidth": 0,
                "borderColor": "#ccc"
            }
        },
        "boxplot": {
            "itemStyle": {
                "borderWidth": 0,
                "borderColor": "#ccc"
            }
        },
        "parallel": {
            "itemStyle": {
                "borderWidth": 0,
                "borderColor": "#ccc"
            }
        },
        "sankey": {
            "itemStyle": {
                "borderWidth": 0,
                "borderColor": "#ccc"
            }
        },
        "funnel": {
            "itemStyle": {
                "borderWidth": 0,
                "borderColor": "#ccc"
            }
        },
        "gauge": {
            "itemStyle": {
                "borderWidth": 0,
                "borderColor": "#ccc"
            }
        },
        "candlestick": {
            "itemStyle": {
                "color": "#eb5454",
                "color0": "#47b262",
                "borderColor": "#eb5454",
                "borderColor0": "#47b262",
                "borderWidth": 1
            }
        },
        "graph": {
            "itemStyle": {
                "borderWidth": 0,
                "borderColor": "#ccc"
            },
            "lineStyle": {
                "width": 1,
                "color": "#aaa"
            },
            "symbolSize": 4,
            "symbol": "emptyCircle",
            "smooth": false,
            "color": [
                "#041439",
                "#0a1e4f",
                "#122a63",
                "#1c3778",
                "#29468d",
                "#3958a5",
                "#4b6bbc",
                "#6080d2"
            ],
            "label": {
                "color": "#eee"
            }
        },
        "map": {
            "itemStyle": {
                "areaColor": "#eee",
                "borderColor": "#444",
                "borderWidth": 0.5
            },
            "label": {
                "color": "#000"
            },
            "emphasis": {
                "itemStyle": {
                    "areaColor": "rgba(255,215,0,0.8)",
                    "borderColor": "#444",
                    "borderWidth": 1
                },
                "label": {
                    "color": "rgb(100,0,0)"
                }
            }
        },
        "geo": {
            "itemStyle": {
                "areaColor": "#eee",
                "borderColor": "#444",
                "borderWidth": 0.5
            },
            "label": {
                "color": "#000"
            },
            "emphasis": {
                "itemStyle": {
                    "areaColor": "rgba(255,215,0,0.8)",
                    "borderColor": "#444",
                    "borderWidth": 1
                },
                "label": {
                    "color": "rgb(100,0,0)"
                }
            }
        },
        "categoryAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#6E7079"
                }
            },
            "axisTick": {
                "show": true,
                "lineStyle": {
                    "color": "#6E7079"
                }
            },
            "axisLabel": {
                "show": true,
                "color": "#6E7079"
            },
            "splitLine": {
                "show": false,
                "lineStyle": {
                    "color": [
                        "#E0E6F1"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "rgba(250,250,250,0.2)",
                        "rgba(210,219,238,0.2)"
                    ]
                }
            }
        },
        "valueAxis": {
            "axisLine": {
                "show": false,
                "lineStyle": {
                    "color": "#6E7079"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#6E7079"
                }
            },
            "axisLabel": {
                "show": true,
                "color": "#6E7079"
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#E0E6F1"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "rgba(250,250,250,0.2)",
                        "rgba(210,219,238,0.2)"
                    ]
                }
            }
        },
        "logAxis": {
            "axisLine": {
                "show": false,
                "lineStyle": {
                    "color": "#6E7079"
                }
            },
            "axisTick": {
                "show": false,
                "lineStyle": {
                    "color": "#6E7079"
                }
            },
            "axisLabel": {
                "show": true,
                "color": "#6E7079"
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#E0E6F1"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "rgba(250,250,250,0.2)",
                        "rgba(210,219,238,0.2)"
                    ]
                }
            }
        },
        "timeAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#6E7079"
                }
            },
            "axisTick": {
                "show": true,
                "lineStyle": {
                    "color": "#6E7079"
                }
            },
            "axisLabel": {
                "show": true,
                "color": "#6E7079"
            },
            "splitLine": {
                "show": false,
                "lineStyle": {
                    "color": [
                        "#E0E6F1"
                    ]
                }
            },
            "splitArea": {
                "show": false,
                "areaStyle": {
                    "color": [
                        "rgba(250,250,250,0.2)",
                        "rgba(210,219,238,0.2)"
                    ]
                }
            }
        },
        "toolbox": {
            "iconStyle": {
                "borderColor": "#999"
            },
            "emphasis": {
                "iconStyle": {
                    "borderColor": "#666"
                }
            }
        },
        "legend": {
            "textStyle": {
                "color": "#333"
            }
        },
        "tooltip": {
            "axisPointer": {
                "lineStyle": {
                    "color": "#ccc",
                    "width": 1
                },
                "crossStyle": {
                    "color": "#ccc",
                    "width": 1
                }
            }
        },
        "timeline": {
            "lineStyle": {
                "color": "#DAE1F5",
                "width": 2
            },
            "itemStyle": {
                "color": "#A4B1D7",
                "borderWidth": 1
            },
            "controlStyle": {
                "color": "#A4B1D7",
                "borderColor": "#A4B1D7",
                "borderWidth": 1
            },
            "checkpointStyle": {
                "color": "#316bf3",
                "borderColor": "fff"
            },
            "label": {
                "color": "#A4B1D7"
            },
            "emphasis": {
                "itemStyle": {
                    "color": "#FFF"
                },
                "controlStyle": {
                    "color": "#A4B1D7",
                    "borderColor": "#A4B1D7",
                    "borderWidth": 1
                },
                "label": {
                    "color": "#A4B1D7"
                }
            }
        },
        "visualMap": {
            "color": [
                "#bf444c",
                "#d88273",
                "#f6efa6"
            ]
        },
        "dataZoom": {
            "handleSize": "undefined%",
            "textStyle": {}
        },
        "markPoint": {
            "label": {
                "color": "#eee"
            },
            "emphasis": {
                "label": {
                    "color": "#eee"
                }
            }
        }
    };
    echarts.registerTheme('theme', theme);

};

WW.registerFactory(new ChartFactory(), "chart/chart");