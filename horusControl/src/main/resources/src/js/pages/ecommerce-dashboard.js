import newCustomersChartsInit from '../theme/charts/echarts/new-customers';
import payingCustomerChartInit from '../theme/charts/echarts/paying-customer-chart';
import projectionVsActualChartInit from '../theme/charts/echarts/projection-vs-actual-chart';
import returningCustomerChartInit from '../theme/charts/echarts/returning-customer-chart';
import topCouponsChartInit from '../theme/charts/echarts/top-coupons-chart';
import totalOrdersChartInit from '../theme/charts/echarts/total-orders-chart';
import totalSalesChartInit from '../theme/charts/echarts/total-sales-chart';
import leafletInit from '../theme/leaflet-top-regions.js';
import revenueMapInit from '../theme/revenue-map';

const { docReady } = window.phoenix.utils;

window.revenueMapInit = revenueMapInit;
docReady(totalSalesChartInit);
docReady(newCustomersChartsInit);
docReady(topCouponsChartInit);
docReady(projectionVsActualChartInit);
docReady(returningCustomerChartInit);
docReady(payingCustomerChartInit);
docReady(totalOrdersChartInit);
docReady(leafletInit);
