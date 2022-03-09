apt update
apt install -y --no-install-recommends docker.io
echo 'nameserver 2a03:7900:2:0:31:3:104:161' > /etc/resolv.conf
echo '{"ipv6":true, "fixed-cidr-v6":"fd00::/64"}' > /etc/docker/daemon.json
ip6tables -t nat -I POSTROUTING -j MASQUERADE
systemctl restart docker
mkdir -p /etc/letsencrypt/live/chaowenguo.eu.org
