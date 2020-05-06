class AsciiCharSequence implements CharSequence {

    private byte[] array;

    // implementation
    public AsciiCharSequence(byte[] array) {
        this.array = array;
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public char charAt(int index) {
        return (char)array[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        int length = end - start;
        byte[] subByteArray = new byte[length];
        for(int i = start, j = 0; i < end; i++, j++ ) {
            subByteArray[j] = this.array[i];
        }
        CharSequence result = new AsciiCharSequence(subByteArray);
        return result;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("");
        for(int i = 0; i < length(); i++) {
            stringBuffer.append(charAt(i));
        }
        return stringBuffer.toString();
    }
}