package file.security;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

    private MD5() {
    }

    public static String md5(final String password) {
        return password == null ? null : DigestUtils.md5Hex(password);
    }
}
