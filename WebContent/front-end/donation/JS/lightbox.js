(function() {

  let overlay = document.querySelector('.overlayContainer'),
      largeImage = document.querySelector('.largeImage');

  const hideOverlay = () => {
    overlay.removeEventListener('click', hideOverlay, false);
    overlay.classList.remove('opacity');

    setTimeout(function() {
      largeImage.removeAttribute('src');
      largeImage.removeAttribute('alt');
      overlay.classList.remove('display');
    }, 400);
  }

  function lightbox(event) {
    const caption = document.querySelector('.imageCaption');
    let href, alt;

    event.preventDefault();
    href = this.getAttribute('href');
    alt = this.children[0].getAttribute('alt');

    largeImage.setAttribute('src', href);
    largeImage.setAttribute('alt', alt);
    caption.innerHTML = alt;
    overlay.classList.add('display');

    setTimeout(function() { overlay.classList.add('opacity'); }, 25);
    setTimeout(function() { overlay.addEventListener('click', hideOverlay, false); }, 400);
  }

  /***Event Listener***/
  const runCode = () => {
    const image = document.querySelectorAll('.imageLink');
    if ( image ) {
      for ( var i = 0; i < image.length; i++ ) {
        image[i].addEventListener('click', lightbox, false);
      }
    }
  }

  runCode();

})();
  //Old version below (compatible with older browser versions)

  //Version 1.0
  /*
  var image = document.querySelectorAll('.imageLink'),
      overlay = document.querySelector('.overlayContainer'),
      largeImage = document.querySelector('.largeImage'),
      caption = document.querySelector('.imageCaption'),
      display = new RegExp('(^| )' + 'display' + '($| )', 'g'),
      opacity = new RegExp('(^| )' + 'opacity' + '($| )', 'g'),
      href,
      alt;

  function hideOverlay() {
    overlay.removeEventListener('click', hideOverlay, false);
    overlay.className = overlay.className.replace(opacity, '');

    setTimeout(function() {
      largeImage.removeAttribute('src');
      largeImage.removeAttribute('alt');
      overlay.className = overlay.className.replace(display, '');
    }, 400);
  }

  function lightbox(event) {
    event.preventDefault();
    href = this.getAttribute('href');
    alt = this.children[0].getAttribute('alt');

    largeImage.setAttribute('src', href);
    largeImage.setAttribute('alt', alt);
    caption.innerHTML = alt;
    overlay.className += ' display';

    setTimeout(function() { overlay.className += ' opacity'; }, 25);
    setTimeout(function() { overlay.addEventListener('click', hideOverlay, false); }, 400);
  }
  */

  /***Event Listener***/
  /*if ( overlay ) {
    for ( var i = 0; i < image.length; i++ ) {
      image[i].addEventListener('click', lightbox, false);
    }
  }*/  
