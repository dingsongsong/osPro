package org.osPro.model;

public class Mutex {
	private Integer readCount = 0;
	private Integer rMutex = 1;
	private Integer wMutex = 1;

	public void readerWait(User user, Integer currentTime) {
		if (user.getStartTime() != -1 || readCount >=5
				|| user.getArriveTime() > currentTime||rMutex==0) {
			return;
		}
		rWait();
		if (readCount == 0) {
			wWait();
		}
		readCount++;
		rSignal();
		user.setStartTime(currentTime);

	}

	public void readerSignal(User user, Integer currentTime) {
		if (user.getStartTime() != -1
				&& (user.getStartTime() + user.getSeriveTime()) == currentTime) {
			rWait();
			readCount--;
			if (readCount == 0) {
				wSignal();
			}
			rSignal();
		}
	}

	public void writerWait(User user, Integer currentTime) {
		if (readCount == 0) {
			if (user.getStartTime() != -1 || user.getArriveTime() > currentTime) {
				return;
			}
			wWait();
			rWait();
			readCount++;
			user.setStartTime(currentTime);
		}
	}

	public void writerSignal(User user, Integer currentTime) {
		if (user.getStartTime() != -1
				&& (user.getStartTime() + user.getSeriveTime()) == currentTime) {
			readCount--;
			rSignal();
			wSignal();
		}
	}

	public void run(User user, Integer currentTime) {
		if (user.getType() == 1) {
			readerWait(user, currentTime);
			readerSignal(user, currentTime);
		} else {
			writerWait(user, currentTime);
			writerSignal(user, currentTime);
		}
	}

	public synchronized void rWait() {
		try {
			if (rMutex == 0) {
				wait();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		rMutex = 0;
	}

	public synchronized void wWait() {
		try {
			if (wMutex == 0) {
				wait();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		wMutex = 0;
	}

	public synchronized void rSignal() {
		rMutex = 1;
		notify();
	}

	public synchronized void wSignal() {
		wMutex = 1;
		notify();
	}
}
