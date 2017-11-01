package view.colorparse;

public class  RGB{
        public int red;
        public int green;
        public int blue;

        public RGB(int r, int g, int b) {
            this.red = r;
            this.green = g;
            this.blue = b;
        }

        @Override
        public String toString() {
            return "RGB{" +
                    "r=" + red +
                    ", g=" + green +
                    ", b=" + blue +
                    '}';
        }
    }