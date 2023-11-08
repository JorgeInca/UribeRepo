/*eslint-disable*/
/*-----------------------------------------------
|   Top navigation opacity on scroll
-----------------------------------------------*/

const responsiveNavItemsInit = () => {
  const { resize } = window.phoenix.utils;
  const Selector = {
    NAV_ITEM: '[data-nav-item]',
    NAVBAR: '[data-navbar]',
    DROPDOWN: '[data-more-item]',
    CATEGORY_LIST: '[data-category-list]',
    CATEGORY_BUTTON: '[data-category-btn]'
  };

  const navbarEl = document.querySelector(Selector.NAVBAR);

  const navbar = () => {
    const navbarWidth = navbarEl.clientWidth;
    const dropdown = navbarEl.querySelector(Selector.DROPDOWN);
    const dropdownWidth = dropdown.clientWidth;
    const navbarContainerWidth = navbarWidth - dropdownWidth;
    const elements = navbarEl.querySelectorAll(Selector.NAV_ITEM);
    const categoryBtn = navbarEl.querySelector(Selector.CATEGORY_BUTTON);
    const categoryBtnWidth = categoryBtn.clientWidth;

    let totalItemsWidth = 0;
    dropdown.style.display = 'none';

    elements.forEach(item => {
      const itemWidth = item.clientWidth;

      totalItemsWidth = totalItemsWidth + itemWidth;

      if (
        totalItemsWidth + categoryBtnWidth + dropdownWidth >
          navbarContainerWidth &&
        !item.classList.contains('dropdown')
      ) {
        dropdown.style.display = 'block';
        item.style.display = 'none';
        const link = item.firstChild;
        const linkItem = link.cloneNode(true);

        navbarEl.querySelector('.category-list').appendChild(linkItem);
      }
    });
    const dropdownMenu = navbarEl.querySelectorAll('.dropdown-menu .nav-link');

    dropdownMenu.forEach(item => {
      item.classList.remove('nav-link');
      item.classList.add('dropdown-item');
    });
  };

  const hideDropdown = () => {
    const dropdownItems = navbarEl.querySelectorAll('.dropdown-item');
    const navbarEle = navbarEl.querySelector('.dropdown');
    if (dropdownItems.length <= 0) {
      // navbarEle.style.display = 'none';
    }
  };

  if (navbarEl) {
    window.addEventListener('load', () => {
      navbar();
      // hideDropdown();
    });

    resize(() => {
      const navElements = navbarEl.querySelectorAll(Selector.NAV_ITEM);
      const dropElements = navbarEl.querySelectorAll(Selector.CATEGORY_LIST);

      navElements.forEach(item => item.removeAttribute('style'));
      dropElements.forEach(item => (item.innerHTML = ''));
      navbar();
      // hideDropdown();
    });

    const navbarLinks = navbarEl.querySelectorAll('.nav-link');

    navbarEl.addEventListener('click', function (e) {
      for (let x = 0; x < navbarLinks.length; x++) {
        navbarLinks[x].classList.remove('active');
      }
      if (e.target.closest('li')) {
        e.target.closest('li').classList.add('active');
      }
    });
  }
};
export default responsiveNavItemsInit;
