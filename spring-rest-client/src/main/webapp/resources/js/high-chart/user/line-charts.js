LineChart = {
		// Basic Line Charts with Static Data
		BasicLineChart : function(){
			Highcharts.chart('container', {
				credits: {
					enabled: false
				},
				title: {
					text: 'Monthly Average Temperature',
					x: -20 //center
				},
				subtitle: {
					text: 'Source: WorldClimate.com',
					x: -20
				},
				xAxis: {
					categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
						'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
				},
				yAxis: {
					title: {
						text: 'Temperature (°C)'
					},
					plotLines: [{
						value: 0,
						width: 1,
						color: '#808080'
					}]
				},
				tooltip: {
					valueSuffix: '°C'
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'middle',
					borderWidth: 0
				},
				series: [{
					name: 'Tokyo',
					data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
				}, {
					name: 'New York',
					data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
				}, {
					name: 'Berlin',
					data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
				}, {
					name: 'London',
					data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
				}]
			});
		},

		AjaxLoadedLineChart : function(){
			var options = {
					chart: {
						renderTo: 'ajax-loaded-data',
						type: 'line'
					},
					credits: {
						enabled: false
					},
					title: {
						text: 'Monthly Average Temperature',
						x: -20 //center
					},
					subtitle: {
						text: 'Source: WorldClimate.com',
						x: -20
					},
					xAxis: {
						categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
							'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
					},
					yAxis: {
						title: {
							text: 'Population (Milions)'
						},
						plotLines: [{
							value: 0,
							width: 1,
							color: '#808080'
						}]
					},
					/*legend: {
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'middle',
						borderWidth: 0
					},
					tooltip: {
						valueSuffix: 'Milion'
					},
					*/
					/*xAxis: {
		                tickInterval: 7 * 24 * 3600 * 1000, // one week
		                tickWidth: 0,
		                gridLineWidth: 1,
		                labels: {
		                    align: 'left',
		                    x: 3,
		                    y: -3
		                }
		            },
		            yAxis: [{ // left y axis
		                title: {
		                    text: null
		                },
		                labels: {
		                    align: 'left',
		                    x: 3,
		                    y: 16,
		                    format: '{value:.,0f}'
		                },
		                showFirstLabel: false
		            }, { // right y axis
		                linkedTo: 0,
		                gridLineWidth: 0,
		                opposite: true,
		                title: {
		                    text: null
		                },
		                labels: {
		                    align: 'right',
		                    x: -3,
		                    y: 16,
		                    format: '{value:.,0f}'
		                },
		                showFirstLabel: false
		            }],
		            */
		            legend: {
		            	layout: 'vertical',
		            	align: 'right',
		                verticalAlign: 'middle',
		                y: 20,
		                floating: true,
		                borderWidth: 0
		            },
		            tooltip: {
		                shared: true,
		                crosshairs: true
		            },
		            plotOptions: {
		                series: {
		                    cursor: 'pointer',
		                    point: {
		                        events: {
		                            click: function (e) {
		                                hs.htmlExpand(null, {
		                                    pageOrigin: {
		                                        x: e.pageX || e.clientX,
		                                        y: e.pageY || e.clientY
		                                    },
		                                    headingText: this.series.name,
		                                    maincontentText: Highcharts.dateFormat('%A, %b %e, %Y', this.x) + ':<br/> ' +
		                                        this.y + ' Population',
		                                    width: 200
		                                });
		                            }
		                        }
		                    },
		                    marker: {
		                        lineWidth: 1
		                    }
		                }
		            },
					series: []
			};

			$.post($('#highChart-getAllCountryCode').val(), function(countryCodesFromServer){
				$.post($('#highChart-getPopulation').val(), function(serverData) {
					for(c=0; c < countryCodesFromServer.length; c++){
						var json = {
								name : null,
								lineWidth: 4,
								marker: {
				                    radius: 4
				                },
								data : []
						}
						for(i=0; i<serverData.length; i++){
							if(countryCodesFromServer[c] == serverData[i].countryCode){
								json.name = countryCodesFromServer[c];
								json.data.push(parseInt(serverData[i].population));
							}
						}
						if(json.data.length > 8 && json.data.length < 10){
							options.series.push(json);
						}
					}
					var chart = new Highcharts.Chart(options);
				});
			});
		},
}
