/* eslint-disable no-new */
/*-----------------------------------------------
|                    Phoenix Offcanvas
-----------------------------------------------*/

const phoenixOffcanvasInit = () => {
  const { getData } = window.phoenix.utils;
  const toggleEls = document.querySelectorAll(
    "[data-phoenix-toggle='offcanvas']"
  );
  const offcanvasBackdrop = document.querySelector('[data-phoenix-backdrop]');
  const offcanvasBodyScroll = document.querySelector('[data-phoenix-scroll]');
  const offcanvasFaq = document.querySelector('.faq');
  const offcanvasFaqShow = document.querySelector('.faq-sidebar');

  const showFilterCol = offcanvasEl => {
    offcanvasEl.classList.add('show');
    if (!offcanvasBodyScroll) {
      document.body.style.overflow = 'hidden';
    }
  };
  const hideFilterCol = offcanvasEl => {
    offcanvasEl.classList.remove('show');
    document.body.style.removeProperty('overflow');
  };

  if (toggleEls) {
    toggleEls.forEach(toggleEl => {
      const offcanvasTarget = getData(toggleEl, 'phoenix-target');
      const offcanvasTargetEl = document.querySelector(offcanvasTarget);
      const closeBtn = offcanvasTargetEl.querySelectorAll(
        "[data-phoenix-dismiss='offcanvas']"
      );
      toggleEl.addEventListener('click', () => {
        showFilterCol(offcanvasTargetEl);
      });
      if (closeBtn) {
        closeBtn.forEach(el => {
          el.addEventListener('click', () => {
            hideFilterCol(offcanvasTargetEl);
          });
        });
      }
      if (offcanvasBackdrop) {
        offcanvasBackdrop.addEventListener('click', () => {
          hideFilterCol(offcanvasTargetEl);
        });
      }
    });
  }

  if (offcanvasFaq) {
    if (offcanvasFaqShow.classList.contains('show')) {
      offcanvasFaq.classList.add = 'newFaq';
    }
  }
};

export default phoenixOffcanvasInit;
