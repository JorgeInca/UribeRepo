const faqTabInit = () => {
  const triggerEls = document.querySelectorAll('[data-vertical-category-tab]');
  const offcanvasEle = document.querySelector(
    '[data-vertical-category-offcanvas]'
  );
  const filterEles = document.querySelectorAll('[data-category-filter]');
  const faqSubcategoryTabs = document.querySelectorAll(
    '.faq-subcategory-tab .nav-item'
  );

  if (offcanvasEle) {
    const offcanvas =
      window.bootstrap.Offcanvas?.getOrCreateInstance(offcanvasEle);

    triggerEls.forEach(el => {
      el.addEventListener('click', () => {
        offcanvas.hide();
      });
    });
  }

  if (filterEles) {
    filterEles.forEach(el => {
      if (el.classList.contains('active')) {
        faqSubcategoryTabs.forEach(item => {
          if (
            !item.classList.contains(el.getAttribute('data-category-filter')) &&
            el.getAttribute('data-category-filter') !== 'all'
          ) {
            item.classList.add('d-none');
          }
        });
      }
      el.addEventListener('click', () => {
        faqSubcategoryTabs.forEach(item => {
          if (el.getAttribute('data-category-filter') === 'all') {
            item.classList.remove('d-none');
          } else if (
            !item.classList.contains(el.getAttribute('data-category-filter'))
          ) {
            item.classList.add('d-none');
          }
        });
      });
    });
  }
};

export default faqTabInit;
