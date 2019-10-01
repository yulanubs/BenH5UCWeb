package cn.kotlincloud.user.service.entity.token;

import java.io.Serializable;

public class Tokens implements Serializable {
    
    /** 
    * @Fields serialVersionUID : TODO
    */ 
    private static final long serialVersionUID = -754659525548951914L;
    private String signature;
    private long timestamp;
    
    public Tokens(String signature, long timestamp) {
        if (signature == null)
            throw new IllegalArgumentException("signature can not be null");
        
        this.timestamp = timestamp;
        this.signature = signature;
    }
    
    public Tokens(String signature) {
        if (signature == null)
            throw new IllegalArgumentException("signature can not be null");
        
        this.signature = signature;
    }
    
    /**
     * Returns a string containing the unique signatureentifier assigned to this token.
     */
    public String getSignature() {
        return signature;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    /**
     * timestamp timestamp token.
     */
    public int hashCode() {
        return signature.hashCode();
    }
    
    public boolean equals(Object object) {
        if (object instanceof Token)
            return ((Tokens)object).signature.equals(this.signature);
        return false;
    }

    @Override
    public String toString() {
        return "Token [signature=" + signature + ", timestamp=" + timestamp
                + "]";
    }
    
    
}