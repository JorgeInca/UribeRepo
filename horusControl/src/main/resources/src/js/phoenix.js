/* eslint-disable no-new */
/* eslint-disable import/no-extraneous-dependencies */
import utils, { docReady } from './utils';

import docComponentInit from './docs';
import advanceAjaxTableInit from './theme/advance-ajax-table';
import anchorJSInit from './theme/anchor';
import bigPictureInit from './theme/bigPicture';
import bulkSelectInit, { BulkSelect } from './theme/bulk-select';
import basicEchartsInit from './theme/charts/echarts/basic-echarts';
import reportsDetailsChartInit from './theme/charts/echarts/reports-details-chart';
import chatInit from './theme/chat';
import choicesInit from './theme/choices';
import copyLink from './theme/copyLink';
import countupInit from './theme/countUp';
import createBoardInit from './theme/create-board';
import detectorInit from './theme/detector';
import dropdownOnHover from './theme/dropdown-on-hover';
import dropzoneInit from './theme/dropzone';
import featherIconsInit from './theme/featherIcons';
import flatpickrInit from './theme/flatpickr';
import formValidationInit from './theme/form-validation';
import { fullCalendarInit } from './theme/fullcalendar';
import glightboxInit from './theme/glightbox';
import initMap from './theme/googleMap';
import iconCopiedInit from './theme/icons';
import isotopeInit from './theme/isotope';
import listInit from './theme/list';
import lottieInit from './theme/lottie';
import modalInit from './theme/modal';
import navbarComboInit from './theme/navbar-combo';
import navbarShadowOnScrollInit from './theme/navbar-shadow-on-scroll';
import navbarInit from './theme/navbar-soft-on-scroll';
import handleNavbarVerticalCollapsed from './theme/navbar-vertical';
import phoenixOffcanvasInit from './theme/phoenix-offcanvas';
import picmoInit from './theme/picmo';
import popoverInit from './theme/popover';
import productDetailsInit from './theme/product-details';
import quantityInit from './theme/quantity';
import randomColorInit from './theme/randomColor';
import ratingInit from './theme/rater';
import responsiveNavItemsInit from './theme/responsiveNavItems';
import searchInit from './theme/search';
import simplebarInit from './theme/simplabar';
import sortableInit from './theme/sortable';
import supportChatInit from './theme/support-chat';
import swiperInit from './theme/swiper';
import themeControl from './theme/theme-control';
import tinymceInit from './theme/tinymce';
import toastInit from './theme/toast';
import todoOffcanvasInit from './theme/todoOffCanvas';
import tooltipInit from './theme/tooltip';
import wizardInit from './theme/wizard';
import faqTabInit from './theme/faq-tab';
import kanbanInit from './theme/kanban';
import towFAVerificarionInit from './theme/2fa-verification';

window.initMap = initMap;
docReady(detectorInit);
docReady(simplebarInit);
docReady(toastInit);
docReady(tooltipInit);
docReady(featherIconsInit);
docReady(basicEchartsInit);
docReady(bulkSelectInit);
docReady(listInit);
docReady(anchorJSInit);
docReady(popoverInit);
docReady(formValidationInit);
docReady(docComponentInit);
docReady(swiperInit);
docReady(productDetailsInit);
docReady(ratingInit);
docReady(quantityInit);
docReady(dropzoneInit);
docReady(choicesInit);
docReady(tinymceInit);
docReady(responsiveNavItemsInit);
docReady(flatpickrInit);
docReady(iconCopiedInit);
docReady(isotopeInit);
docReady(bigPictureInit);
docReady(countupInit);
docReady(phoenixOffcanvasInit);
docReady(todoOffcanvasInit);
docReady(wizardInit);
docReady(reportsDetailsChartInit);
docReady(glightboxInit);
docReady(themeControl);
docReady(searchInit);
docReady(handleNavbarVerticalCollapsed);
docReady(navbarInit);
docReady(themeControl);
docReady(navbarComboInit);
docReady(fullCalendarInit);
docReady(picmoInit);

docReady(chatInit);
docReady(modalInit);
docReady(lottieInit);
docReady(navbarShadowOnScrollInit);
docReady(dropdownOnHover);
docReady(supportChatInit);
docReady(sortableInit);

docReady(copyLink);
docReady(randomColorInit);
docReady(faqTabInit);
docReady(createBoardInit);
docReady(advanceAjaxTableInit);
docReady(kanbanInit);
docReady(towFAVerificarionInit);

docReady(() => {
  const selectedRowsBtn = document.querySelector('[data-selected-rows]');
  const selectedRows = document.getElementById('selectedRows');
  if (selectedRowsBtn) {
    const bulkSelectEl = document.getElementById('bulk-select-example');
    const bulkSelectInstance =
      window.phoenix.BulkSelect.getInstance(bulkSelectEl);
    selectedRowsBtn.addEventListener('click', () => {
      selectedRows.innerHTML = JSON.stringify(
        bulkSelectInstance.getSelectedRows(),
        undefined,
        2
      );
    });
  }
});

export default {
  utils,
  BulkSelect
};
