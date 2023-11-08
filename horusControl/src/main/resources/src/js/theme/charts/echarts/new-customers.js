import { echartSetOption } from './echarts-utils';
/* -------------------------------------------------------------------------- */
/*                     Echart Bar Member info                                 */
/* -------------------------------------------------------------------------- */

const newCustomersChartsInit = () => {
  const { getColor, getData, getDates } = window.phoenix.utils;
  const $echartNewCustomersCharts = document.querySelector(
    '.echarts-new-customers'
  );
  const tooltipFormatter = params => {
    const currentDate = window.dayjs(params[0].axisValue);
    const prevDate = window.dayjs(params[0].axisValue).subtract(1, 'month');

    const result = params.map((param, index) => ({
      value: param.value,
      date: index > 0 ? prevDate : currentDate,
      color: param.color
    }));

    let tooltipItem = ``;
    result.forEach((el, index) => {
      tooltipItem += `<h6 class="fs--1 text-700 ${
        index > 0 && 'mb-0'
      }"><span class="fas fa-circle me-2" style="color:${el.color}"></span>
      ${el.date.format('MMM DD')} : ${el.value}
    </h6>`;
    });
    return `<div class='ms-1'>
              ${tooltipItem}
            </div>`;
  };

  if ($echartNewCustomersCharts) {
    const userOptions = getData($echartNewCustomersCharts, 'echarts');
    const chart = window.echarts.init($echartNewCustomersCharts);
    const getDefaultOptions = () => ({
      tooltip: {
        trigger: 'axis',
        padding: 10,
        backgroundColor: getColor('gray-100'),
        borderColor: getColor('gray-300'),
        textStyle: { color: getColor('dark') },
        borderWidth: 1,
        transitionDuration: 0,
        axisPointer: {
          type: 'none'
        },
        formatter: tooltipFormatter
      },
      xAxis: [
        {
          type: 'category',
          data: getDates(
            new Date('5/1/2022'),
            new Date('5/7/2022'),
            1000 * 60 * 60 * 24
          ),
          show: true,
          boundaryGap: false,
          axisLine: {
            show: true,
            lineStyle: { color: getColor('gray-200') }
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            formatter: value => window.dayjs(value).format('DD MMM'),
            showMinLabel: true,
            showMaxLabel: false,
            color: getColor('gray-800'),
            align: 'left',
            interval: 5,
            fontFamily: 'Nunito Sans',
            fontWeight: 600,
            fontSize: 12.8
          }
        },
        {
          type: 'category',
          position: 'bottom',
          show: true,
          data: getDates(
            new Date('5/1/2022'),
            new Date('5/7/2022'),
            1000 * 60 * 60 * 24
          ),
          axisLabel: {
            formatter: value => window.dayjs(value).format('DD MMM'),
            interval: 130,
            showMaxLabel: true,
            showMinLabel: false,
            color: getColor('gray-800'),
            align: 'right',
            fontFamily: 'Nunito Sans',
            fontWeight: 600,
            fontSize: 12.8
          },
          axisLine: {
            show: false
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: false
          },
          boundaryGap: false
        }
      ],
      yAxis: {
        show: false,
        type: 'value',
        boundaryGap: false
      },
      series: [
        {
          type: 'line',
          data: [150, 100, 300, 200, 250, 180, 250],
          showSymbol: false,
          symbol: 'circle',
          lineStyle: {
            width: 2,
            color: getColor('gray-200')
          },
          emphasis: {
            lineStyle: {
              color: getColor('gray-200')
            }
          }
        },
        {
          type: 'line',
          data: [200, 150, 250, 100, 500, 400, 600],
          lineStyle: {
            width: 2,
            color: getColor('primary')
          },
          showSymbol: false,
          symbol: 'circle'
        }
      ],
      grid: { left: 0, right: 0, top: 5, bottom: 20 }
    });
    echartSetOption(chart, userOptions, getDefaultOptions);
  }
};

export default newCustomersChartsInit;
