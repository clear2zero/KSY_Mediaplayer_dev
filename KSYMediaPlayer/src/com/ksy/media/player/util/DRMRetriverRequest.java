package com.ksy.media.player.util;

import android.os.Parcel;
import android.os.Parcelable;

public class DRMRetriverRequest implements Parcelable {

	private String mKSCDRMHost;
	private String mKSCDRMPort;
	private String mCustomerName;
	private DRMMethod mDRMMethod;
	private String mSignature;
	private String mAccessKey;
	private String mExpire;
	private String mNoce;
	private String mCekUrl;
	private int mVersion;

	public DRMRetriverRequest(Parcel p) {

	}

	public static final Parcelable.Creator<DRMRetriverRequest> CREATOR = new Parcelable.Creator<DRMRetriverRequest>() {

		@Override
		public DRMRetriverRequest createFromParcel(Parcel p) {

			return new DRMRetriverRequest(p);
		}

		@Override
		public DRMRetriverRequest[] newArray(int size) {

			return new DRMRetriverRequest[size];
		}
	};

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel p, int flags) {

		p.writeString(mKSCDRMHost);
		p.writeString(mKSCDRMPort);
		p.writeString(mCustomerName);
		p.writeString(mDRMMethod.name());
		p.writeString(mSignature);
		p.writeString(mAccessKey);
		p.writeString(mExpire);
		p.writeString(mNoce);
		p.writeString(mCekUrl);
		p.writeInt(mVersion);

	}

	enum DRMMethod {

		NewCek("NewCek"), GetCek("GetCek"), DelCek("DelCek");

		private DRMMethod(String method) {

		}
	}

}
