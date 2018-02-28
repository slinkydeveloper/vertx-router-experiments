#!/usr/bin/env node
const ChartjsNode = require('chartjs-node');
const _ = require('lodash');
const fs = require('fs');

let social_data = JSON.parse(fs.readFileSync('./output.json', 'utf8'));
let social_tests = JSON.parse(fs.readFileSync('../src/main/resources/paths.json', 'utf8'));
social_tests = social_tests.paths;

function runRegex(regexp, s) {
    let a = regexp.exec(s);
    let r = undefined;
    if (!_.isEmpty(a))
        r = a[1];
    regexp.lastIndex = 0;
    return r;
}

function buildChartOptionsForTestsWithGroups(data, tests, groups) {
    let datasets = [];

    for (let group of groups) {
        // Score
        let chartData =
            data
                .filter((value) => runRegex(group.regexp, value.benchmark) != undefined)
                .map((value) => ({
                    index: parseInt(runRegex(group.regexp, value.benchmark)),
                    score: value.primaryMetric.score,
                    rawData: _.flatten(value.primaryMetric.rawData)
                }));
        chartData.sort((a, b) => a.index - b.index);

        datasets.push({
            label: group.label,
            backgroundColor: group.mainColor,
            borderColor: group.mainColor,
            fill: false,
            data: chartData.map((value) => (group.scale) ? (value.score * group.scale) : value.score)
        });

        // Raw Data
        for (let i = 0; i < chartData[0].rawData.length; i++) {
            datasets.push({
                label: group.label + "-Iteration" + (i + 1),
                backgroundColor: group.transparentColor,
                borderColor: group.transparentColor,
                fill: false,
                data: chartData.map((value) => (group.scale) ? (value.rawData[i] * group.scale) : value.rawData[i])
            });
        }
    }

    let allowedLabels = groups.map((group) => group.label);

    return {
        type: 'line',
        data: {
            labels: tests,
            datasets: datasets
        },
        options: {
            showLines: true,
            lineTension: 0,
            scales: {
                xAxes: [{
                    ticks: {
                        fontSize: 12,
                        display: true,
                        autoSkip: false
                    }
                }]
            },
            legend: {
                display: true,
                labels: {
                    filter: (l) => allowedLabels.includes(l.text)
                }
            }
        }
    }
}

function buildChartOptionsForAverage(data, datasets) {
    let obj = {
        type: 'bar',
        data: {
            labels: [],
            datasets: [
                {
                    label: "Values",
                    backgroundColor: [],
                    data: []
                }
            ]
        },
        options: {
            legend: {
                display: false
            },
            scales: {
                xAxes: [{
                    ticks: {
                        fontSize: 12,
                        display: true,
                        autoSkip: false
                    }
                }]
            }
        }
    };
    for (let set of datasets) {
        obj.data.labels.push(set.label);
        obj.data.datasets[0].backgroundColor.push(set.color);
        obj.data.datasets[0].data.push(data.find((el) => el.benchmark === set.name).primaryMetric.score);
    }
    return obj;
}

function doChart(length, height, chartOptions, imageName) {
    let chartNode = new ChartjsNode(length, height);
    chartNode.drawChart(chartOptions)
        .then(buffer => {
            return chartNode.getImageStream('image/png');
        }).then(streamResult => {
        return chartNode.writeImageToFile('image/png', imageName);
    }).catch(err => {
        console.error(err)
    });
}

doChart(400, 800, buildChartOptionsForAverage(social_data,
    [
        {
            "label": "ECTree",
            "name": "io.slinkydeveloper.bench.ECTreeRouterBenchmark.routingRandomRoutes",
            "color": "rgba(255, 0, 0, 1)"
        },
        {
            "label": "ECList",
            "name": "io.slinkydeveloper.bench.ECListRouterBenchmark.routingRandomRoutes",
            "color": "rgba(0, 0, 255, 1)"
        },
        {
            "label": "SkipList",
            "name": "io.slinkydeveloper.bench.SkipListRouterBenchmark.routingRandomRoutes",
            "color": "rgba(0, 255, 0, 1)"
        }
    ]), "../out/social_average.png");

const basicGroups = [
    {
        label: "ECTree",
        regexp: /.*route([0-9]{1,2})ECTreeRouter$/g,
        mainColor: "rgba(255, 0, 0, 1)",
        transparentColor: "rgba(255, 0, 0, 0.3)"
    },
    {
        label: "ECList",
        regexp: /.*route([0-9]{1,2})ECListRouter$/g,
        mainColor: "rgba(0, 255, 0, 1)",
        transparentColor: "rgba(0, 255, 0, 0.3)"
    },
    {
        label: "SkipList",
        regexp: /.*route([0-9]{1,2})SkipListRouter$/g,
        mainColor: "rgba(255, 255, 0, 1)",
        transparentColor: "rgba(255, 255, 0, 0.3)"
    }
];

const withLoadGroups = [
    {
        label: "ECTree with load",
        regexp: /.*route([0-9]{1,2})ECTreeRouterWithLoad$/g,
        mainColor: "rgba(255, 150, 0, 1)",
        transparentColor: "rgba(255, 150, 0, 0.3)",
        scale: 11
    },
    {
        label: "ECList with load",
        regexp: /.*route([0-9]{1,2})ECListRouterWithLoad$/g,
        mainColor: "rgba(150, 255, 0, 1)",
        transparentColor: "rgba(150, 255, 0, 0.3)",
        scale: 11
    },
    {
        label: "SkipList with load",
        regexp: /.*route([0-9]{1,2})SkipListRouterWithLoad$/g,
        mainColor: "rgba(255, 255, 150, 1)",
        transparentColor: "rgba(255, 255, 150, 0.3)",
        scale: 11
    }
];

const allGroups = basicGroups.concat(withLoadGroups);

doChart(2000, 600, buildChartOptionsForTestsWithGroups(social_data, social_tests, basicGroups), "../out/basic_social.png");
doChart(2000, 600, buildChartOptionsForTestsWithGroups(social_data, social_tests, withLoadGroups), "../out/with_load_social.png");
doChart(2000, 600, buildChartOptionsForTestsWithGroups(social_data, social_tests, allGroups), "../out/social_complete.png");