window.onload = function() {
    let i = 0;
    const imgs = document.querySelectorAll('.banner-img');

    function cambiarImagenes() {
        imgs[i].classList.remove('active');
        i = (i + 1) % imgs.length;
        imgs[i].classList.add('active');
    }

    setInterval(cambiarImagenes, 5000);
};
