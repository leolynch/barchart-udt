package io.netty.channel.socket.nio;

import io.netty.buffer.MessageBuf;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.ChannelSocketUDT;

public class NioUdtByteAcceptorChannel extends NioUdtBaseAcceptorChannel {

	protected NioUdtByteAcceptorChannel() {
		super(TypeUDT.STREAM);
	}

	@Override
	protected int doReadMessages(final MessageBuf<Object> buf) throws Exception {

		logger.debug("### do read");

		final ChannelSocketUDT channelUDT = javaChannel().accept();

		logger.debug("### channelUDT=" + channelUDT);

		if (channelUDT == null) {
			return 0;
		}

		buf.add(new NioUdtByteConnectorChannel( //
				this, channelUDT.socketUDT().getSocketId(), channelUDT));

		return 1;

	}

}
