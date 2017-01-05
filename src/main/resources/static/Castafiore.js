"Generated from Java with JSweet 1.1.1 - http://www.jsweet.org";
var Castafiore = (function () {
    function Castafiore() {
    }
    Castafiore.prototype.doRender = function (data) {
        for (var index121 = 0; index121 < data.length; index121++) {
            var o = data[index121];
            {
                console.info(o);
            }
        }
    };
    Castafiore.prototype.load = function () {
        $.getJSON("/castafiore/ui/sds?casta_applicationid=documentation", function (t, u, v) {
            console.info(u);
            return null;
        });
    };
    return Castafiore;
}());
