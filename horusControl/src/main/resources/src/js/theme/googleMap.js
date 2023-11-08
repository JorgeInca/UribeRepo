/*-----------------------------------------------
|   Gooogle Map
-----------------------------------------------*/

function initMap() {
  const { getData } = window.phoenix.utils;
  const themeController = document.body;
  const $googlemaps = document.querySelectorAll('[data-googlemap]');
  if ($googlemaps.length && window.google) {
    const createControlBtn = (map, type) => {
      const controlButton = document.createElement('button');
      controlButton.classList.add(type);
      controlButton.innerHTML =
        type === 'zoomIn'
          ? '<span class="fas fa-plus text-black"></span>'
          : '<span class="fas fa-minus text-black"></span>';

      controlButton.addEventListener('click', () => {
        const zoom = map.getZoom();
        if (type === 'zoomIn') {
          map.setZoom(zoom + 1);
        }
        if (type === 'zoomOut') {
          map.setZoom(zoom - 1);
        }
      });

      return controlButton;
    };
    const mapStyles = {
      SnazzyCustomLight: [
        {
          featureType: 'administrative',
          elementType: 'all',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'on'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.text.fill',
          stylers: [
            {
              color: '#525b75'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.text.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.icon',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative.country',
          elementType: 'geometry.stroke',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#ffffff'
            }
          ]
        },
        {
          featureType: 'administrative.province',
          elementType: 'geometry.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'landscape',
          elementType: 'geometry',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#E3E6ED'
            }
          ]
        },
        {
          featureType: 'landscape.natural',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'poi',
          elementType: 'all',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'road',
          elementType: 'all',
          stylers: [
            {
              color: '#eff2f6'
            }
          ]
        },
        {
          featureType: 'road',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'road.arterial',
          elementType: 'all',
          stylers: [
            {
              visibility: 'on'
            }
          ]
        },
        {
          featureType: 'road.arterial',
          elementType: 'geometry',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#eff2f6'
            }
          ]
        },
        {
          featureType: 'road.arterial',
          elementType: 'labels.text.fill',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#9fa6bc'
            }
          ]
        },
        {
          featureType: 'road.arterial',
          elementType: 'labels.text.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'road.local',
          elementType: 'geometry.fill',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#eff2f6'
            }
          ]
        },
        {
          featureType: 'road.local',
          elementType: 'labels.text.fill',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#9fa6bc'
            }
          ]
        },
        {
          featureType: 'road.local',
          elementType: 'labels.text.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit',
          elementType: 'labels.icon',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit.line',
          elementType: 'geometry',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit.line',
          elementType: 'labels.text',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit.station.airport',
          elementType: 'geometry',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit.station.airport',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'water',
          elementType: 'geometry',
          stylers: [
            {
              color: '#F5F7FA'
            }
          ]
        },
        {
          featureType: 'water',
          elementType: 'labels',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        }
      ],
      SnazzyCustomDark: [
        {
          featureType: 'administrative',
          elementType: 'all',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels',
          stylers: [{ visibility: 'on' }]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.text.fill',
          stylers: [
            {
              color: '#8a94ad'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.text.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative',
          elementType: 'labels.icon',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'administrative.country',
          elementType: 'geometry.stroke',
          stylers: [
            { visibility: 'on' },
            {
              color: '#000000'
            }
          ]
        },
        {
          featureType: 'administrative.province',
          elementType: 'geometry.stroke',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'landscape',
          elementType: 'geometry',
          stylers: [{ visibility: 'on' }, { color: '#222834' }]
        },
        {
          featureType: 'landscape.natural',
          elementType: 'labels',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'poi',
          elementType: 'all',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'road',
          elementType: 'all',
          stylers: [{ color: '#141824' }]
        },
        {
          featureType: 'road',
          elementType: 'labels',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'road.arterial',
          elementType: 'all',
          stylers: [
            {
              visibility: 'on'
            }
          ]
        },
        {
          featureType: 'road.arterial',
          elementType: 'geometry',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#141824'
            }
          ]
        },
        {
          featureType: 'road.arterial',
          elementType: 'labels.text.fill',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#525b75'
            }
          ]
        },
        {
          featureType: 'road.arterial',
          elementType: 'labels.text.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'road.local',
          elementType: 'geometry.fill',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#141824'
            }
          ]
        },
        {
          featureType: 'road.local',
          elementType: 'labels.text.fill',
          stylers: [
            {
              visibility: 'on'
            },
            {
              color: '#67718A'
            }
          ]
        },
        {
          featureType: 'road.local',
          elementType: 'labels.text.stroke',
          stylers: [
            {
              visibility: 'off'
            }
          ]
        },
        {
          featureType: 'transit',
          elementType: 'labels.icon',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit.line',
          elementType: 'geometry',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit.line',
          elementType: 'labels.text',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit.station.airport',
          elementType: 'geometry',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'transit.station.airport',
          elementType: 'labels',
          stylers: [{ visibility: 'off' }]
        },
        {
          featureType: 'water',
          elementType: 'geometry',
          stylers: [{ color: '#0f111a' }]
        },
        {
          featureType: 'water',
          elementType: 'labels',
          stylers: [{ visibility: 'off' }]
        }
      ]
    };

    $googlemaps.forEach(itm => {
      const latLng = getData(itm, 'latlng').split(',');
      const markerPopup = itm.innerHTML;
      const zoom = getData(itm, 'zoom');
      const mapElement = itm;
      const mapStyle = getData(itm, 'phoenixTheme');

      if (getData(itm, 'phoenixTheme') === 'streetview') {
        const pov = getData(itm, 'pov');
        const mapOptions = {
          position: { lat: Number(latLng[0]), lng: Number(latLng[1]) },
          pov,
          zoom,
          gestureHandling: 'none',
          scrollwheel: false
        };

        return new window.google.maps.StreetViewPanorama(
          mapElement,
          mapOptions
        );
      }

      const mapOptions = {
        zoom,
        minZoom: 1.2,
        clickableIcons: false,
        zoomControl: false,
        zoomControlOptions: {
          position: window.google.maps.ControlPosition.LEFT
        },
        scrollwheel: getData(itm, 'scrollwheel'),
        disableDefaultUI: true,
        center: new window.google.maps.LatLng(latLng[0], latLng[1]),
        styles:
          window.config.config.phoenixTheme === 'dark'
            ? mapStyles.SnazzyCustomDark
            : mapStyles[mapStyle || 'SnazzyCustomLight']
      };

      const map = new window.google.maps.Map(mapElement, mapOptions);
      const infoWindow = new window.google.maps.InfoWindow({
        content: markerPopup
      });

      // Create the DIV to hold the control.
      const controlDiv = document.createElement('div');
      controlDiv.classList.add('google-map-control-btn');
      // Create the control.
      const zoomInBtn = createControlBtn(map, 'zoomIn');
      const zoomOutBtn = createControlBtn(map, 'zoomOut');
      // Append the control to the DIV.
      controlDiv.appendChild(zoomInBtn);
      controlDiv.appendChild(zoomOutBtn);

      map.controls[window.google.maps.ControlPosition.LEFT].push(controlDiv);

      const marker = new window.google.maps.Marker({
        position: new window.google.maps.LatLng(latLng[0], latLng[1]),
        // icon,
        map
      });

      marker.addListener('click', () => {
        infoWindow.open(map, marker);
      });

      themeController &&
        themeController.addEventListener(
          'clickControl',
          ({ detail: { control, value } }) => {
            if (control === 'phoenixTheme') {
              map.set(
                'styles',
                value === 'dark'
                  ? mapStyles.SnazzyCustomDark
                  : mapStyles.SnazzyCustomLight
              );
            }
          }
        );

      // return null;
    });
  }
}

export default initMap;
