import completedTaskChartInit from '../theme/charts/echarts/completed-task-chart';
import topCouponsChartInit from '../theme/charts/echarts/top-coupons-chart';

const { docReady } = window.phoenix.utils;
docReady(completedTaskChartInit);
docReady(topCouponsChartInit);
